package trans;

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

@SuppressWarnings("all") public class parse_templatelang_file_0_0 extends Strategy 
{ 
  public static parse_templatelang_file_0_0 instance = new parse_templatelang_file_0_0();

  @Override public IStrategoTerm invoke(Context context, IStrategoTerm term)
  { 
    context.push("parse_templatelang_file_0_0");
    Fail6:
    { 
      IStrategoTerm u_10706 = null;
      IStrategoTerm v_10706 = null;
      u_10706 = term;
      v_10706 = trans.constTemplateLangTbl;
      term = parse_file_2_1.instance.invoke(context, u_10706, strsglr_perror_0_0.instance, strsglr_report_parse_error_0_0.instance, v_10706);
      if(term == null)
        break Fail6;
      context.popOnSuccess();
      if(true)
        return term;
    }
    context.popOnFailure();
    return null;
  }
}