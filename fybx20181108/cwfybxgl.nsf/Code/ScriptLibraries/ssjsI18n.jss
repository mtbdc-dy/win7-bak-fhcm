//取得服务器的更新时间，并保存在当前数据库的applicationScope对象中
function getUts(){
	var uts = applicationScope.get("uts"); 
	if(uts == null){
		var strKeyValue = @DbColumn("indishare/indidomcfg.nsf","vwUpdateInfo",1);
		if(strKeyValue){
			applicationScope.put("uts",@If(@Elements(strKeyValue)>1,@Subset(strKeyValue,1),strKeyValue));
			uts = applicationScope.get("uts");
		}
	}
	return "uts=" + uts;		
}

//设置xsp页面引入的dojo属性
function fnSetXspDojoConfig() {
    try {
        //取得系统时间戳
        var curUts = getUts();
        var resources = new com.ibm.xsp.resource.ScriptResource();
        resources.setComponent(view);
        resources.setClientSide(true);
        resources.setContents("dojo.config.cacheBust='" + curUts + "'");
        view.getResources().add(0, resources);

        //为主题中的css、js文件添加时间戳
        var resources = view.getResources();
        var count = resources.size();
        for (var i = 0; i < count; i++) {
            var resource = resources.get(i);
            if (resource instanceof com.ibm.xsp.resource.StyleSheetResource) {
                var src = resource.getHref();
                if (src.indexOf("&uts") === -1) {
                    resource.setHref(src + "?open&" + curUts);
                }
            } else if (resource instanceof com.ibm.xsp.resource.ScriptResource) {
                if (resource.getSrc() && resource.isClientSide()) {
                    var src = resource.getSrc();
                    if (src.indexOf("&uts") === -1) {
                        resource.setSrc(src + "?open&" + curUts);
                    }
                }
            }
        }
    } catch(err) {
        print("fnSetXspDojoConfig is error");
    }
} 

function fnSetLocalei18n(){
    var lang = cookie.get("indi_locale");
    if (lang) {
        lang = lang.getValue();
        var serverLang = context.getLocaleString().toLowerCase();
        serverLang = serverLang.replace('_','-'); //'zh_CN'
        if ( serverLang != lang) {
        		context.setLocaleString(lang);
                context.reloadPage();          
        }
    }
}

smartdot = {};
smartdot.i18n = {
    getString: function(bundleId, defaultValue, contextI18n) {

        /**
         * 获取语言包内的字符串
         *
         * defaultValue:
         * 		默认值，获取不到或者locale为zh-cn时直接返回
         * contextI18n:
         * 		可选参数，如果有则包装dojo.string.substitute的功能
         */

        // 通过bundleId生成packageName、bundleName和attrName
        // 例如：indiplatform.tylc.subject 生成 indiplatform, tylc, subject
        var arr = bundleId.split('.');
        var packageName = arr.shift(); 
        var attrName = arr.pop();
        var bundleName = arr.join('.');
        var bundle,customBundle ;
        var defaultLocale = "zh-cn";
        var lang = cookie.get("indi_locale");      
        if (lang) {
        	lang = lang.getValue();
        }else{
        	lang = defaultLocale;
        }
        //如果是zh-cn，直接返回默认值即可
        if (lang !== defaultLocale) {
                bundle = this.getI18nStringByLang(packageName, bundleName, attrName, lang);
        }
        bundle = bundle || defaultValue;
        //用项目自定义的包覆盖
        customBundle = this.getI18nStringByLang('custom', packageName,bundleName+'.'+attrName, lang);
        bundle = customBundle || bundle;
       
        var result = bundle;
        if (contextI18n) {
            result = this.substitute(result, contextI18n)
        }
        return result;
    },

    substitute:function(template, contextI18n){
    	var that = this;
    	var rtn =template.replace(/\$\{([^\s\:\}]+)(?:\:([^\s\:\}]+))?\}/g, 
    			function(match, key){
    				var value = that.getObject(key, contextI18n);
    				return  null == value ? match: value.toString();
    			}); // String
    	rtn= template.replace(/#\{([^\s\:\}]+)(?:\:([^\s\:\}]+))?\}/g, //ssjs将不是以$开始的字符串中 ${***} 中的$转化成了#
    			function(match, key){
    				var value = that.getObject(key, contextI18n);
    				return  null == value ? match: value.toString();
    			}); // String
    	rtn= template.replace(/@\{([^\s\:\}]+)(?:\:([^\s\:\}]+))?\}/g, //${} 和  #{}是ssjs计算的标记，特殊情况下用@{}
    			function(match, key){
    				var value = that.getObject(key, contextI18n);
    				return  null == value ? match: value.toString();
    			}); // String
    	return rtn;
    },
    getI18nStringByLang:function(packageName, bundleName,attrName, lang){
    	var key:java.util.Vector =new java.util.Vector();
    	key.add(bundleName);
    	key.add(lang);
    	key.add(attrName);
    	var rtn;
    	var dbLang:NotesDatabase  = session.getDatabase("", "indishare/i18n_"+packageName+".nsf");
    	if(!dbLang.isOpen()) return rtn;
    	var vw:NotesView  = dbLang.getView("vwI18nString");
    	var docLang:NotesDocument ;    	
    	if(vw){
    		docLang = vw.getDocumentByKey(key, true);
    		if(docLang){
    			rtn = docLang.getItemValueString('value');
    		}
    	}
    	return rtn;
    },
    getObject: function(prop,contextObj){
    	var ps = prop.split('.');
    	var rtnObj;
    	var obj = contextObj?contextObj:{};
    	for(var i=0,p;i<ps.length;i++){
    		p=ps[i];
    		if(p in obj){    			
    			obj = contextObj[p];
    			rtnObj = obj;
    		}else{
    			rtnObj = null;
    		}
    	}
    	return rtnObj
    }
}