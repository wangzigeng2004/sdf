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

@SuppressWarnings("all") public class break_bp_$Template$Lang$Cached$Option_0_0 extends Strategy 
{ 
  public static break_bp_$Template$Lang$Cached$Option_0_0 instance = new break_bp_$Template$Lang$Cached$Option_0_0();

  @Override public IStrategoTerm invoke(Context context, IStrategoTerm term)
  { 
    context.push("break_bp_TemplateLangCachedOption_0_0");
    Fail592:
    { 
      IStrategoTerm n_9762 = null;
      n_9762 = term;
      term = dr_break_bp_0_1.instance.invoke(context, n_9762, generated.const116);
      if(term == null)
        break Fail592;
      context.popOnSuccess();
      if(true)
        return term;
    }
    context.popOnFailure();
    return null;
  }
}