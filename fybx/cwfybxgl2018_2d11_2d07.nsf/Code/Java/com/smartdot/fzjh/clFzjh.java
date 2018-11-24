package com.smartdot.fzjh;

import java.util.Vector;

import lotus.domino.*;

public class clFzjh {
	
	public static String fnGetValidServer(Session session,String strServerName){
		return fnGetValidServerControl(session,strServerName);
	}
	
	public static String fnGetValidServer(String strServerName){
		Session session = com.ibm.xsp.model.domino.DominoUtils.getCurrentSession();
		return fnGetValidServerControl(session,strServerName);
	}
	
	private static String fnGetValidServerControl(Session session,String strServerName){
		Database dbAppMap = null;
		View vwServerForFzjhApi = null;
		Document docApp = null;	
		String strReturn = "";
		try{
			if( strServerName.equals("")){
				return "";
			}			
			dbAppMap = session.getDatabase("","indishare/appmap.nsf");
			if( !dbAppMap.isOpen()){
				return strServerName;
			}
			vwServerForFzjhApi = dbAppMap.getView("vwServerByFzjhAPI");
			if(null == vwServerForFzjhApi){
				return strServerName;
			}
			docApp = vwServerForFzjhApi.getDocumentByKey(strServerName,true);
			if(null != docApp){
				strReturn = docApp.getItemValueString("strValidServer");
			}
			if( strReturn.equals("")){
				strReturn = strServerName;
			}
			
		}catch(Exception ex){
			System.out.println(ex.toString());
		} finally {
			vwServerForFzjhApi = null;
			docApp = null;
			dbAppMap = null;
		}
		return strReturn;
	}

	public static boolean fnIsMasterServer(Session session,String strServerName){
		return fnIsMasterServerControl(session,strServerName);
	}
	
	public static boolean fnIsMasterServer(String strServerName){
		Session session = com.ibm.xsp.model.domino.DominoUtils.getCurrentSession();
		return fnIsMasterServerControl(session,strServerName);
	}
	
	private static boolean fnIsMasterServerControl(Session session,String strServerName){
		Database dbAppMap = null;
		View vwServerByName = null;
		Document docApp = null;
		try{
			dbAppMap = session.getDatabase("","indishare/appmap.nsf");
			if( !dbAppMap.isOpen()){
				return true;
			}
			vwServerByName = dbAppMap.getView("VwHostByServer");
			docApp = vwServerByName.getDocumentByKey(strServerName,true);
			
			if(null ==  docApp){
				return true;
			}
			
			if( docApp.getItemValueString("strIsMaster").equals("0")){
				return false;
			}else{
				return true;
			}
						
		}catch(Exception ex){
			System.out.println(ex.toString());
		}finally {
			vwServerByName = null;
			docApp = null;
			dbAppMap = null;
		}
		return true;
	}
	
	public static boolean fnIsSameServer(Session session,String strServerNameFirst,String strServerNameSecond){
		return fnIsSameServerControl(session,strServerNameFirst,strServerNameSecond);
	}
	
	public static boolean fnIsSameServer(String strServerNameFirst,String strServerNameSecond){
		Session session = com.ibm.xsp.model.domino.DominoUtils.getCurrentSession();
		return fnIsSameServerControl(session,strServerNameFirst,strServerNameSecond);
	}
	
	private static boolean fnIsSameServerControl(Session session,String strServerNameFirst,String strServerNameSecond){
		Database dbAppMap = null;
		View vwServerForFzjhApi = null;
		Document docApp = null;
		String strFirstGroup = "";
		String strSecondGroup ="";
		try{
			
			if( strServerNameFirst.equalsIgnoreCase(strServerNameSecond) ){
				return true;
			}
			
			dbAppMap = session.getDatabase("","indishare/appmap.nsf");
			if( !dbAppMap.isOpen()){
				return false;
			}
			vwServerForFzjhApi = dbAppMap.getView("vwServerByFzjhAPI");
			if(null == vwServerForFzjhApi){
				return false;
			}
			
			docApp = vwServerForFzjhApi.getDocumentByKey(strServerNameFirst,true);
			if(null != docApp){
				strFirstGroup = docApp.getItemValueString("strServerGroup");
			}
			
			if( strFirstGroup.equals("")){
				strFirstGroup = strServerNameFirst;
			}
			
			docApp = vwServerForFzjhApi.getDocumentByKey(strServerNameSecond,true);
			if(null != docApp){
				strSecondGroup = docApp.getItemValueString("strServerGroup");
			}
			
			if( strSecondGroup.equals("")){
				strSecondGroup = strServerNameSecond;
			}
		
		}catch(Exception ex){
			System.out.println(ex.toString());
		}finally {
			vwServerForFzjhApi = null;
			docApp = null;
			dbAppMap = null;
		}
		
		if(strFirstGroup.equalsIgnoreCase(strSecondGroup)){
			return true;
		}else{
			return false;
		}
	}
	
	public static String fnGetServerLogic(Session session,String strServerName){
		return fnGetServerLogicControl(session,strServerName);
	}
	
	public static String fnGetServerLogic(String strServerName){
		Session session = com.ibm.xsp.model.domino.DominoUtils.getCurrentSession();
		return fnGetServerLogicControl(session,strServerName);
	}
	
	private static String fnGetServerLogicControl(Session session,String strServerName){
		Database dbAppMap = null;
		View vwServerForFzjhApi = null;
		Document docApp = null;
		String strReturn = "";
		try{
			if( strServerName.equals("")){
				return "";
			}
			dbAppMap = session.getDatabase("","indishare/appmap.nsf");
			if( !dbAppMap.isOpen()){
				return strServerName;
			}
			vwServerForFzjhApi = dbAppMap.getView("vwServerByFzjhAPI");
			if( null == vwServerForFzjhApi){
				return strServerName;
			}
			
			docApp = vwServerForFzjhApi.getDocumentByKey(strServerName,true);
			if(null != docApp){
				strReturn = docApp.getItemValueString("strServerGroup");
			}
			
			if( strReturn.equals("")){
				strReturn = strServerName;
			}
			
		}catch(Exception ex){
			System.out.println(ex.toString());
		}finally {
			vwServerForFzjhApi = null;
			docApp = null;
			dbAppMap = null;
		}
		return strReturn;
	}
	
	public static boolean fnCreateReplicaToSameServer(Session session,Database dbSource){
		return fnCreateReplicaToSameServerControl(session,dbSource);
	}
	
	public static boolean fnCreateReplicaToSameServer(Database dbSource){
		Session session = com.ibm.xsp.model.domino.DominoUtils.getCurrentSession();
		return fnCreateReplicaToSameServerControl(session,dbSource);
	}
	
	@SuppressWarnings("unchecked")
	private static boolean fnCreateReplicaToSameServerControl(Session session,Database dbSource){
		Database dbAppMap = null;
		View vwServerForFzjhApi = null;
		DocumentCollection dcApp = null;
		Document docApp = null;
		Database dbMsg = null;
		Document docMsg = null;
		Database dbName = null;
		Name nmServer = null;
		String strSrcServer = "";
		String strSrcDbPath = "";
		String strDstServer = "";
		Boolean blnFlag = true;
		try{
			nmServer = session.createName(dbSource.getServer());
			strSrcServer = nmServer.getCanonical();
			strSrcDbPath = dbSource.getFilePath().replace("\\", "/");
			//������Ϣ�ĵ�����������ʧ��ʱʹ�ã�
			dbMsg = session.getDatabase("", "indishare/msgengine.nsf");
			if( dbMsg.isOpen()){
				docMsg = dbMsg.createDocument();
				docMsg.replaceItemValue("form", "frmMessage");
				docMsg.replaceItemValue("fldObjectID", "createreplica_fzjh");
				docMsg.replaceItemValue("fldActionID", "createrep");
				docMsg.replaceItemValue("fldSourceServer", strSrcServer);
				docMsg.replaceItemValue("fldSourceDbPath", strSrcDbPath);
			}
			//��ȡ�������ݷ������б�
			dbAppMap = session.getDatabase("","indishare/appmap.nsf");
			if( !dbAppMap.isOpen()){
				System.out.println("���ݿ�["+strSrcDbPath+"]��������ʱ�����޷���ȷ��ȡ���ݿ�indishare/appmap.nsf�����飡");
				blnFlag = false;
			}
			vwServerForFzjhApi = dbAppMap.getView("vwServerByFzjhAPI");
			if( null == vwServerForFzjhApi){
				System.out.println("���ݿ�["+strSrcDbPath+"]��������ʱ�����޷���ȷ��ȡ���ݿ�indishare/appmap.nsf�е���ͼvwServerByFzjhAPI�����飡");
				blnFlag = false;
			}
			if( blnFlag ){
				//��ȡ�������ݷ������б�
				dcApp = vwServerForFzjhApi.getAllDocumentsByKey(fnGetServerLogic(session,strSrcServer),true);
				if(dcApp.getCount() > 0){
					Vector strMultiple = new Vector();
					docApp = dcApp.getFirstDocument();
					//������ȡ���ı��ݷ�����
					while(null != docApp){
						strDstServer = docApp.getItemValueString("fldServer");
						//�ڱ��ݷ������ϴ�������
						if( !(strSrcServer.toLowerCase()).equals(strDstServer.toLowerCase()) && !"".equals(strDstServer) ){
							dbName = session.getDatabase(strDstServer, "names.nsf");
							//�����������������ݷ�����
							if( dbName.isOpen() ){
								dbSource.createReplica(strDstServer, strSrcDbPath);
							}else{
								//���ݷ�����崻���ԭ�򲻿�����
								strMultiple.addElement(strDstServer);
							}
						}
						docApp = dcApp.getNextDocument(docApp);
					}
					if(strMultiple.size()>0){
						if(null != docMsg){
							docMsg.appendItemValue("fldDstServer", strMultiple);
						}
						blnFlag = false;
					}
				}
			}
			//����������ʧ�ܵķ�����������Ҫ������Ϣ�ĵ������ڶ�ʱ������
			if( !blnFlag ){
				if(null != docMsg){
					docMsg.save(true,false);
				}
			}
		}catch(Exception ex){
			System.out.println(ex.toString());
		}finally {
			nmServer = null;
			dbName = null;
			docMsg = null;
			dbMsg = null;
			docApp = null;
			dcApp = null;
			vwServerForFzjhApi = null;
			dbAppMap = null;
		}
		return blnFlag;
	}
}