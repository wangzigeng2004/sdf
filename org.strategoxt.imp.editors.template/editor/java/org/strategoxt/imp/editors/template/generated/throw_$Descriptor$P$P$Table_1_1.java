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

@SuppressWarnings("all") public class throw_$Descriptor$P$P$Table_1_1 extends Strategy 
{ 
  public static throw_$Descriptor$P$P$Table_1_1 instance = new throw_$Descriptor$P$P$Table_1_1();

  @Override public IStrategoTerm invoke(Context context, IStrategoTerm term, Strategy g_9766, IStrategoTerm f_9766)
  { 
    context.push("throw_DescriptorPPTable_1_1");
    Fail615:
    { 
      IStrategoTerm i_9766 = null;
      i_9766 = term;
      term = dr_throw_1_2.instance.invoke(context, i_9766, g_9766, f_9766, generated.const126);
      if(term == null)
        break Fail615;
      context.popOnSuccess();
      if(true)
        return term;
    }
    context.popOnFailure();
    return null;
  }
}