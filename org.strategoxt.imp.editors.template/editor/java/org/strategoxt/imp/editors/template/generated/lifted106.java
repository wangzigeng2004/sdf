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

@SuppressWarnings("all") final class lifted106 extends Strategy 
{ 
  public static final lifted106 instance = new lifted106();

  @Override public IStrategoTerm invoke(Context context, IStrategoTerm term)
  { 
    Fail891:
    { 
      IStrategoTerm term130 = term;
      Success370:
      { 
        Fail892:
        { 
          term = string_is_layout_0_0.instance.invoke(context, term);
          if(term == null)
            break Fail892;
          { 
            if(true)
              break Fail891;
            if(true)
              break Success370;
          }
        }
        term = term130;
      }
      if(true)
        return term;
    }
    return null;
  }
}