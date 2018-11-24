function fnGetValidServer( strServerName ){
	return com.smartdot.fzjh.clFzjh.fnGetValidServer( strServerName );
}

function fnIsMasterServer( strServerName ){
	return com.smartdot.fzjh.clFzjh.fnIsMasterServer( strServerName );	
}

function fnIsSameServer( strServerNameFirst,strServerNameSecond ){
	return com.smartdot.fzjh.clFzjh.fnIsSameServer( strServerNameFirst,strServerNameSecond );
}

function fnGetServerLogic( strServerName ){
	return com.smartdot.fzjh.clFzjh.fnGetServerLogic( strServerName );
}

function fnCreateReplicaToSameServer( dbSource ){
	return com.smartdot.fzjh.clFzjh.fnCreateReplicaToSameServer( dbSource );
}