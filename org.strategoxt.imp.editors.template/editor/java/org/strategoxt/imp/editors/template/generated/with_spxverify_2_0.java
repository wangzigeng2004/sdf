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

@SuppressWarnings("all") public class with_spxverify_2_0 extends Strategy 
{ 
  public static with_spxverify_2_0 instance = new with_spxverify_2_0();

  @Override public IStrategoTerm invoke(Context context, IStrategoTerm term, Strategy g_9748, Strategy h_9748)
  { 
    context.push("with_spxverify_2_0");
    Fail369:
    { 
      term = with_verify_3_0.instance.invoke(context, term, g_9748, _Id.instance, h_9748);
      if(term == null)
        break Fail369;
      context.popOnSuccess();
      if(true)
        return term;
    }
    context.popOnFailure();
    return null;
  }
}