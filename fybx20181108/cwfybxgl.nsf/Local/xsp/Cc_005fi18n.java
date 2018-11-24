/*
 * Generated file. 
 * 
 * Cc_005fi18n.java
 */
package xsp;

import com.ibm.xsp.page.compiled.AbstractCompiledPage;
import com.ibm.xsp.page.compiled.AbstractCompiledPageDispatcher;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import com.ibm.xsp.page.compiled.NoSuchComponentException;
import com.ibm.xsp.page.compiled.PageExpressionEvaluator;
import java.lang.String;
import javax.faces.el.MethodBinding;
import com.ibm.xsp.component.FacesPageProvider;
import com.ibm.xsp.resource.ScriptResource;
import com.ibm.xsp.resource.DojoModulePathResource;
import javax.faces.el.ValueBinding;
import com.ibm.xsp.resource.DojoModuleResource;
import com.ibm.xsp.component.UIViewRootEx2;
import com.ibm.xsp.component.xp.XspOutputScript;
import java.lang.Object;

@SuppressWarnings("all")
public class Cc_005fi18n extends AbstractCompiledPageDispatcher{
    
    public Cc_005fi18n() {
        super("8.5.2"); // version of xp:dojoModulePath
    }

    protected AbstractCompiledPage createPage(int pageIndex) {
        return new Cc_005fi18nPage();
    }
    
    protected boolean isCustomControl() {
        return true;
    }

    public static class Cc_005fi18nPage extends AbstractCompiledPage {
        
        private static final ComponentInfo[] s_infos = new ComponentInfo[]{
            ComponentInfo.EMPTY_NORMAL, // 0 scriptBlock
            new ComponentInfo(false, new int[]{0}), // 1 view
        };
        
        public Cc_005fi18nPage() {
            super(1, s_infos );
        }
        
        public int getComponentForId(String id) throws NoSuchComponentException { 
            return -1;
        }
        
        public UIComponent createComponent(int id, FacesContext context,
                UIComponent parent, PageExpressionEvaluator evaluator)
                throws NoSuchComponentException { 
            switch (id) {
            case 1:
                return createView(context, parent, evaluator);
            case 0:
                return createScriptBlock(context, parent, evaluator);
            }
            throw new NoSuchComponentException(id);
        }
        
        protected void initIncluderAsRoot(FacesContext context,
                PageExpressionEvaluator evaluator, UIComponent root) {
            String sourceId = "/xp:view[1]/xp:this.beforePageLoad[1]/text()";
            MethodBinding beforePageLoad = evaluator.createMethodBinding(root,
                    "#{javascript:fnSetLocalei18n();}",
                    null,null, sourceId);
            FacesPageProvider asPageProvider = root instanceof FacesPageProvider ?
                    (FacesPageProvider) root : null;
            if( null != asPageProvider ){
                asPageProvider.setBeforePageLoad(beforePageLoad);
            }
            ScriptResource resources = new ScriptResource();
            resources.setComponent(root);
            resources.setClientSide(false);
            resources.setSrc("/ssjsI18n.jss");
            if( null != asPageProvider ){
                asPageProvider.addResource(resources);
            }
            DojoModulePathResource resources2 = new DojoModulePathResource();
            resources2.setComponent(root);
            resources2.setPrefix("smartdot");
            resources2.setUrl("../../dojo/smartdot");
            if( null != asPageProvider ){
                asPageProvider.addResource(resources2);
            }
            ScriptResource resources3 = new ScriptResource();
            resources3.setComponent(root);
            resources3.setClientSide(true);
            String sourceId2 = "/xp:view[1]/xp:this.resources[1]/xp:script[2]/@src";
            String srcExpr = "#{javascript:\'/.ibmxspres/domino/dojo/smartdot/i18n.js?open&\' + getUts()}";
            ValueBinding src = evaluator.createValueBinding(root, srcExpr, sourceId2,String.class);
            resources3.setValueBinding("src", src);
            if( null != asPageProvider ){
                asPageProvider.addResource(resources3);
            }
            DojoModuleResource resources4 = new DojoModuleResource();
            resources4.setComponent(root);
            resources4.setName("smartdot._xhrNsfPatch");
            if( null != asPageProvider ){
                asPageProvider.addResource(resources4);
            }
            DojoModuleResource resources5 = new DojoModuleResource();
            resources5.setComponent(root);
            resources5.setName("smartdot.xpages.i18n_bridge");
            if( null != asPageProvider ){
                asPageProvider.addResource(resources5);
            }
            String sourceId3 = "/xp:view[1]/xp:this.beforeRenderResponse[1]/text()";
            MethodBinding beforeRenderResponse = evaluator.createMethodBinding(root,
                    "#{javascript:fnSetXspDojoConfig()}",
                    null,null, sourceId3);
            if( null != asPageProvider ){
                asPageProvider.setBeforeRenderResponse(beforeRenderResponse);
            }
        }

        private UIComponent createView(FacesContext context, 
                UIComponent parent, PageExpressionEvaluator evaluator) {
            UIViewRootEx2 result = new UIViewRootEx2();
            initViewRoot(result);
            String sourceId = "/xp:view[1]/xp:this.beforePageLoad[1]/text()";
            MethodBinding beforePageLoad = evaluator.createMethodBinding(result,
                    "#{javascript:fnSetLocalei18n();}",
                    null,null, sourceId);
            result.setBeforePageLoad(beforePageLoad);
            ScriptResource resources = new ScriptResource();
            resources.setComponent(result);
            resources.setClientSide(false);
            resources.setSrc("/ssjsI18n.jss");
            result.addResource(resources);
            DojoModulePathResource resources2 = new DojoModulePathResource();
            resources2.setComponent(result);
            resources2.setPrefix("smartdot");
            resources2.setUrl("../../dojo/smartdot");
            result.addResource(resources2);
            ScriptResource resources3 = new ScriptResource();
            resources3.setComponent(result);
            resources3.setClientSide(true);
            String sourceId2 = "/xp:view[1]/xp:this.resources[1]/xp:script[2]/@src";
            String srcExpr = "#{javascript:\'/.ibmxspres/domino/dojo/smartdot/i18n.js?open&\' + getUts()}";
            ValueBinding src = evaluator.createValueBinding(result, srcExpr, sourceId2,String.class);
            resources3.setValueBinding("src", src);
            result.addResource(resources3);
            DojoModuleResource resources4 = new DojoModuleResource();
            resources4.setComponent(result);
            resources4.setName("smartdot._xhrNsfPatch");
            result.addResource(resources4);
            DojoModuleResource resources5 = new DojoModuleResource();
            resources5.setComponent(result);
            resources5.setName("smartdot.xpages.i18n_bridge");
            result.addResource(resources5);
            String sourceId3 = "/xp:view[1]/xp:this.beforeRenderResponse[1]/text()";
            MethodBinding beforeRenderResponse = evaluator.createMethodBinding(result,
                    "#{javascript:fnSetXspDojoConfig()}",
                    null,null, sourceId3);
            result.setBeforeRenderResponse(beforeRenderResponse);
            return result;
        }

        private UIComponent createScriptBlock(FacesContext context, 
                UIComponent parent, PageExpressionEvaluator evaluator) {
            XspOutputScript result = new XspOutputScript();
            String sourceId = "/xp:view[1]/xp:scriptBlock[1]/xp:this.value[1]/text()";
            String valueExpr = "#{javascript:@Implode(\'smartdot.i18n.requireLocalization(\"\'+@List(compositeData.languagePackNames)+\'\")\',\";\")}";
            ValueBinding value = evaluator.createValueBinding(result, valueExpr, sourceId,Object.class);
            result.setValueBinding("value", value);
            result.setType("text/javascript");
            return result;
        }

    }
}
