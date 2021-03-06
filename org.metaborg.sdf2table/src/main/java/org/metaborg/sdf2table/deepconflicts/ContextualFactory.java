package org.metaborg.sdf2table.deepconflicts;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import org.metaborg.sdf2table.grammar.ISymbol;
import org.metaborg.sdf2table.grammar.Production;
import org.metaborg.sdf2table.grammar.Symbol;
import org.metaborg.sdf2table.parsetable.ParseTable;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

public class ContextualFactory implements Serializable {

    private static final long serialVersionUID = -5796688665070378982L;

    private final Map<List<Object>, Context> contexts;
    private final Map<List<Object>, ContextualProduction> contextualProductions;
    private final Map<Set<Object>, ContextualSymbol> contextualSymbols;

    public ContextualFactory() {
        contexts = Maps.newHashMap();
        contextualProductions = Maps.newHashMap();
        contextualSymbols = Maps.newHashMap();
    }

    public Context createContext(int c, ContextType type, ContextPosition position, boolean isIndirect,
        final Map<Integer, Integer> leftmostContextsMapping, final Map<Integer, Integer> rightmostContextsMapping) {
        List<Object> contextFields =
            Lists.newArrayList(c, type, position, isIndirect, leftmostContextsMapping, rightmostContextsMapping);

        if(contexts.containsKey(contextFields)) {
            return contexts.get(contextFields);
        }

        Context context = new Context(c, type, position, isIndirect, leftmostContextsMapping, rightmostContextsMapping);
        contexts.put(contextFields, context);

        return context;
    }

    public ContextualProduction createContextualProduction(Production origProduction, ISymbol lhs, List<ISymbol> rhs,
        int origProductionLabel, ContextualFactory cf) {
        List<Object> contextualProductionFields = Lists.newArrayList(origProduction, lhs, origProductionLabel, cf);
        contextualProductionFields.addAll(rhs);

        if(contextualProductions.containsKey(contextualProductionFields)) {
            return contextualProductions.get(contextualProductionFields);
        }

        ContextualProduction cp = new ContextualProduction(origProduction, lhs, rhs, origProductionLabel, cf);
        contextualProductions.put(contextualProductionFields, cp);

        return cp;
    }

    public ContextualProduction createContextualProduction(Production origProduction, Set<Context> contexts,
        Set<Integer> args, int origProductionLabel, ContextualFactory cf) {
        ISymbol lhs = origProduction.leftHand();
        List<ISymbol> rhs = Lists.newArrayList();

        for(int i = 0; i < origProduction.arity(); i++) {
            if(args.contains(i)) {
                rhs.add(createContextualSymbol(origProduction.getRhs().get(i), contexts, this));
            } else {
                rhs.add(origProduction.rightHand().get(i));
            }
        }

        List<Object> contextualProductionFields = Lists.newArrayList(origProduction, lhs, origProductionLabel, cf);
        contextualProductionFields.addAll(rhs);

        if(contextualProductions.containsKey(contextualProductionFields)) {
            return contextualProductions.get(contextualProductionFields);
        }

        ContextualProduction cp = new ContextualProduction(origProduction, lhs, rhs, origProductionLabel, cf);
        contextualProductions.put(contextualProductionFields, cp);

        return cp;
    }

    public ContextualProduction createContextualProduction(Production origProduction, Set<Context> contexts,
        Queue<ContextualSymbol> contextualSymbols, Set<ContextualSymbol> processedSymbols, int origProductionLabel,
        ParseTable pt) {
        ISymbol lhs = createContextualSymbol(origProduction.getLhs(), contexts, this);
        List<ISymbol> rhs = Lists.newArrayList(origProduction.getRhs());


        // FIXME propagate context considering nullable symbols
        for(Context c : contexts) {
            // contextual tokens for indirect recursion need to be propagated
            // in the opposite direction (i.e., if conflict occurs at the rightmost symbol, they need to be
            // propagated to the leftmost symbol) until they reach the recursive symbol
            if(c.getType() == ContextType.DEEP) {
                if(c.isIndirect()) {
                    ISymbol lefthandSymbolFromContext = pt.productionLabels().inverse().get(c.getContext()).leftHand();
                    for(int i = 0; i < origProduction.arity(); i++) {
                        if((i == 0 && i == origProduction.leftRecursivePosition()
                            && (c.getPosition().equals(ContextPosition.RIGHTMOST)))
                            || (i == origProduction.arity() - 1 && i == origProduction.rightRecursivePosition()
                                && (c.getPosition().equals(ContextPosition.LEFTMOST)))) {
                            ContextualSymbol newSymbol;
                            if(rhs.get(i) instanceof ContextualSymbol) {
                                ISymbol nonContextualSymbol = ((ContextualSymbol) rhs.get(i)).getOrigSymbol();
                                if(lefthandSymbolFromContext.equals(nonContextualSymbol)) {
                                    newSymbol = ((ContextualSymbol) rhs.get(i))
                                        .addContext(createContext(c.getContext(), c.getType(), c.getPosition(), false,
                                            pt.getLeftmostContextsMapping(), pt.getRightmostContextsMapping()));
                                } else {
                                    newSymbol = ((ContextualSymbol) rhs.get(i)).addContext(c);
                                }
                            } else {
                                if(lefthandSymbolFromContext.equals(rhs.get(i))) {
                                    newSymbol = createContextualSymbol(
                                        (Symbol) rhs.get(i), createContext(c.getContext(), c.getType(), c.getPosition(),
                                            false, pt.getLeftmostContextsMapping(), pt.getRightmostContextsMapping()),
                                        this);
                                } else {
                                    newSymbol = createContextualSymbol((Symbol) rhs.get(i), c, this);
                                }
                            }
                            rhs.set(i, newSymbol);
                        }
                    }
                    // propagate contextual tokens considering direct recursion
                } else {
                    // verify all symbols in rhs to consider leftRecursivePosition() or rightRecursivePosition()
                    // that are not 0 or |rhs|
                    for(int i = 0; i < origProduction.arity(); i++) {
                        if((i == 0 && i == origProduction.leftRecursivePosition()
                            && (c.getPosition().equals(ContextPosition.LEFTMOST)))
                            || (i == origProduction.arity() - 1 && i == origProduction.rightRecursivePosition()
                                && (c.getPosition().equals(ContextPosition.RIGHTMOST)))) {
                            ContextualSymbol newSymbol;
                            if(rhs.get(i) instanceof ContextualSymbol) {
                                newSymbol = ((ContextualSymbol) rhs.get(i)).addContext(c);
                            } else {
                                newSymbol = createContextualSymbol((Symbol) rhs.get(i), c, this);
                            }
                            rhs.set(i, newSymbol);
                        }
                    }
                }
            } else if(c.getType() == ContextType.DANGLING) {
                if(c.isIndirect()) {
                    ISymbol lefthandSymbolFromContext = pt.productionLabels().inverse().get(c.getContext()).leftHand();
                    for(int i = 0; i < origProduction.arity(); i++) {
                        if((i == 0 && i == origProduction.leftRecursivePosition())
                            || (i == origProduction.arity() - 1 && i == origProduction.rightRecursivePosition())) {
                            ContextualSymbol newSymbol;
                            if(rhs.get(i) instanceof ContextualSymbol) {
                                ISymbol nonContextualSymbol = ((ContextualSymbol) rhs.get(i)).getOrigSymbol();
                                if(lefthandSymbolFromContext.equals(nonContextualSymbol)) {
                                    newSymbol = ((ContextualSymbol) rhs.get(i))
                                        .addContext(createContext(c.getContext(), c.getType(), c.getPosition(), false,
                                            pt.getLeftmostContextsMapping(), pt.getRightmostContextsMapping()));
                                } else {
                                    newSymbol = ((ContextualSymbol) rhs.get(i)).addContext(c);
                                }
                            } else {
                                if(lefthandSymbolFromContext.equals(rhs.get(i))) {
                                    newSymbol = createContextualSymbol(
                                        (Symbol) rhs.get(i), createContext(c.getContext(), c.getType(), c.getPosition(),
                                            false, pt.getLeftmostContextsMapping(), pt.getRightmostContextsMapping()),
                                        this);
                                } else {
                                    newSymbol = createContextualSymbol((Symbol) rhs.get(i), c, this);
                                }
                            }
                            rhs.set(i, newSymbol);
                        }
                    }
                    // propagate contextual tokens considering direct recursion
                } else {
                    // verify all symbols in rhs to consider leftRecursivePosition() or rightRecursivePosition()
                    // that are not 0 or |rhs|
                    for(int i = 0; i < origProduction.arity(); i++) {
                        if((i == 0 && i == origProduction.leftRecursivePosition())
                            || (i == origProduction.arity() - 1 && i == origProduction.rightRecursivePosition())) {
                            ContextualSymbol newSymbol;
                            if(rhs.get(i) instanceof ContextualSymbol) {
                                newSymbol = ((ContextualSymbol) rhs.get(i)).addContext(c);
                            } else {
                                newSymbol = createContextualSymbol((Symbol) rhs.get(i), c, this);
                            }
                            rhs.set(i, newSymbol);
                        }
                    }
                }
            }

        }

        for(ISymbol s : rhs) {
            if(s instanceof ContextualSymbol) {
                ContextualSymbol new_symbol = (ContextualSymbol) s;
                // need to process new contextual symbols
                if((contextualSymbols != null && processedSymbols != null) && !processedSymbols.contains(new_symbol)
                    && !contextualSymbols.contains(new_symbol)) {
                    contextualSymbols.add(new_symbol);
                }
            }
        }

        List<Object> contextualProductionFields = Lists.newArrayList(origProduction, lhs, origProductionLabel, this);
        contextualProductionFields.addAll(rhs);

        if(contextualProductions.containsKey(contextualProductionFields)) {
            return contextualProductions.get(contextualProductionFields);
        }

        ContextualProduction cp = new ContextualProduction(origProduction, lhs, rhs, origProductionLabel, this);
        contextualProductions.put(contextualProductionFields, cp);

        return cp;
    }

    public ContextualSymbol createContextualSymbol(Symbol s, Set<Context> contexts, ContextualFactory cf) {
        // use Set instead of List to account for the Set<Contexts>
        Set<Object> contextualSymbolFields = Sets.newHashSet(s);
        contextualSymbolFields.addAll(contexts);

        if(contextualSymbols.containsKey(contextualSymbolFields)) {
            ContextualSymbol cs = contextualSymbols.get(contextualSymbolFields);

            for(Context context : contexts) {
                if(context.getType() == ContextType.DEEP || context.getType() == ContextType.DANGLING) {
                    cs.setDeepContextBitmap(cs.deepContexts() | context.getContextBitmap());
                }
            }
            return cs;
        }

        ContextualSymbol cs = new ContextualSymbol(s, contexts, this);
        contextualSymbols.put(contextualSymbolFields, cs);

        return cs;
    }

    public ContextualSymbol createContextualSymbol(Symbol s, Context context, ContextualFactory cf) {
        // use Set instead of List to account for the Set<Contexts>
        Set<Object> contextualSymbolFields = Sets.newHashSet(s);
        contextualSymbolFields.add(context);

        if(contextualSymbols.containsKey(contextualSymbolFields)) {
            ContextualSymbol cs = contextualSymbols.get(contextualSymbolFields);
            cs.setDeepContextBitmap(cs.deepContexts() | context.getContextBitmap());
            return contextualSymbols.get(contextualSymbolFields);
        }

        ContextualSymbol cs = new ContextualSymbol(s, context, this);
        contextualSymbols.put(contextualSymbolFields, cs);

        return cs;
    }

    public ContextualSymbol createContextualSymbol(Symbol s, Set<Context> contexts, long deepContextBitmap,
        ContextualFactory cf) {
        Set<Object> contextualSymbolFields = Sets.newHashSet(s);
        contextualSymbolFields.addAll(contexts);

        if(contextualSymbols.containsKey(contextualSymbolFields)) {
            ContextualSymbol cs = contextualSymbols.get(contextualSymbolFields);
            cs.setDeepContextBitmap(deepContextBitmap);
            return contextualSymbols.get(contextualSymbolFields);
        }

        ContextualSymbol cs = new ContextualSymbol(s, contexts, deepContextBitmap, this);
        contextualSymbols.put(contextualSymbolFields, cs);

        return cs;
    }



}
