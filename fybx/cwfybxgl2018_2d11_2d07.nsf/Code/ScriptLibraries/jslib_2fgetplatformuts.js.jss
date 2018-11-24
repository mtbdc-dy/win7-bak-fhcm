//取得服务器的更新时间，并保存在当前数据库的applicationScope对象中
function getUts(){
	var uts = applicationScope.get("uts"); 
	if(uts == null){
		var strKeyValue = @DbColumn("indishare/indidomcfg.nsf","vwUpdateInfo",1);
		if(strKeyValue){
			applicationScope.put("uts",@If(@Elements(strKeyValue)>1,@Subset(strKeyValue,1),strKeyValue));
		}		
	}
	return "uts=" + uts;		
}