package org.strategoxt.imp.editors.template.generated;

import org.strategoxt.stratego_lib.*;
import org.strategoxt.stratego_lib.*;
import org.strategoxt.stratego_sglr.*;
import org.strategoxt.stratego_gpp.*;
import org.strategoxt.stratego_xtc.*;
import org.strategoxt.stratego_aterm.*;
import org.strategoxt.stratego_sdf.*;
import org.strategoxt.strc.*;
import org.strategoxt.imp.editors.template.generated.*;
import org.strategoxt.java_front.*;
import org.strategoxt.lang.*;
import org.spoofax.interpreter.terms.*;
import static org.strategoxt.lang.Term.*;
import org.spoofax.interpreter.library.AbstractPrimitive;
import java.util.ArrayList;
import java.lang.ref.WeakReference;

@SuppressWarnings("all") public class $Indent$Def_1_0 extends Strategy 
{ 
  public static $Indent$Def_1_0 instance = new $Indent$Def_1_0();

  @Override public IStrategoTerm invoke(Context context, IStrategoTerm term, Strategy r_9752)
  { 
    ITermFactory termFactory = context.getFactory();
    context.push("IndentDef_1_0");
    Fail460:
    { 
      IStrategoTerm w_9928 = null;
      IStrategoTerm v_9928 = null;
      if(term.getTermType() != IStrategoTerm.APPL || Main._consIndentDef_1 != ((IStrategoAppl)term).getConstructor())
        break Fail460;
      v_9928 = term.getSubterm(0);
      IStrategoList annos34 = term.getAnnotations();
      w_9928 = annos34;
      term = r_9752.invoke(context, v_9928);
      if(term == null)
        break Fail460;
      term = termFactory.annotateTerm(termFactory.makeAppl(Main._consIndentDef_1, new IStrategoTerm[]{term}), checkListAnnos(termFactory, w_9928));
      context.popOnSuccess();
      if(true)
        return term;
    }
    context.popOnFailure();
    return null;
  }
}