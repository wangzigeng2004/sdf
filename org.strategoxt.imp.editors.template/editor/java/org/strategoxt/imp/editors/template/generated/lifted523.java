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

@SuppressWarnings("all") final class lifted523 extends Strategy 
{ 
  public static final lifted523 instance = new lifted523();

  @Override public IStrategoTerm invoke(Context context, IStrategoTerm term)
  { 
    Fail1465:
    { 
      IStrategoTerm o_32806 = null;
      if(term.getTermType() != IStrategoTerm.TUPLE || term.getSubtermCount() != 2)
        break Fail1465;
      o_32806 = term.getSubterm(0);
      IStrategoTerm arg781 = term.getSubterm(1);
      term = aux_$Descriptor$P$P$Table_0_1.instance.invoke(context, o_32806, arg781);
      if(term == null)
        break Fail1465;
      if(true)
        return term;
    }
    return null;
  }
}