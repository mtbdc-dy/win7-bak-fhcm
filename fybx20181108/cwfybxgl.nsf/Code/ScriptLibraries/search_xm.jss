function fndoSearch(){
	var strQueryString = "";
	var username = context.getUrlParameter("category");
	if(viewScope.key){
		strQueryString = viewScope.key;
		var arr = username.split("=");
		var brr = arr[1].split("/");
		strQueryString += " and ([nmDealPerson] contains " + brr[0] + "/)";
	}else{
		return "";
	}
	return strQueryString;
}