package org.caeit.cs.client.xd;

public enum MsgType {

	// SMS-SEE
	SERVICEDEPLOY, SERVICELIST, SERVICECOPYLIST, HOSTLIST, UNDEPLOYEDSERVICE, HOSTSIZE,// 服务部署
	SERVICEDIRECTDEPLOY, SERVICEDEPLOYFLAG, SERVICESTATUS, SERVICEDELETE, SETCOPYNUM, SETCCRNUM, FILTER, FILTERSERVICE, SIZENUM,FILTERSIZE,// 服务管理
	NODELIST, SERVICEDETAIL, STRGLIST, SELECTSTRG, SETSTRGPARA, FILTERSTRATEGY, CFILTERSTRATEGY, // 策略管理
	NODEDELETE, NODEDETAIL, NODESTATUS, NODEEXIST,

	NODESET, NODEINSERT, FILTERNODE, CFILTERNODE,

	FLOWLIST, FLOWCOPYLISTRESULT, FLOWCOPYLIST, FLOWLIST2RESULT, FLOWLIST2, FLOWTRANS, BSNSFLOWDEPLOY, FLOWEXECUTE, // 业务流程建模
	SERVFLOWDEPLOY, // 服务流程建模
	FLOWSTATUS, // 流程启停

	// SEE-SMS 结果返回
	SERVICELISTRESULT, HOSTLISTRESULT, SERVICECOPYLISTRESULT, // 服务部署
	SERVICEDEPLOYRESULT, SERVICESTATUSRESULT, SERVICEDELETERESULT, SETCOPYNUMRESULT, SETCCRNUMRESULT, FILTERRESULT, FILTERSERVICERESULT, // 服务管理
	NODELISTRESULT, NODEDELETERESULT, CFILTERNODERESULT, FILTERNODERESULT,

	NODESETRESULT, NODEINSERTRESULT,

	STRGLISTRESULT, SELECTSTRGRESULT, SETSTRGPARARESULT, FILTERSTRATEGYRESULT, CFILTERSTRATEGYRESULT, // 策略管理

	FLOWLISTRESULT, FLOWTRANSRESULT, FLOWDEPLOYRESULT, FLOWEXECUTERESULT, // 业务流程建模
	// 服务流程建模
	FLOWSTATUSRESULT, // 流程启停结果

	// 拓扑结构
	serviceCopyRequest, serviceCopy, HOSTINFO, HOSTHARDDISK, singleServiceCallTimesRequest, singleServiceCallTimes, singleServiceRunTimeRequest, singleServiceRunTime,

	// SMS-SCAS
	SENDTYPE, SENDTYPERESULT, SENDFILE, DeleteService, DeleteServiceResult, FLOWEXECUTE2, StartFlow, changeServiceStatus, EndFlow, ChangeFlowStatus, changeFlowStatus,
	// SCAS-SMS
	SENDFILERESULT, RECEIVEFILERESULT, FLOWEXECUTE2RESULT, StartFlowResult, changeServiceStatusResult, EndFlowResult, ChangeFlowStatusResult, changeFlowStatusResult,

	// SMS-engine
	SENDFLOW,
	// engine-SMS
	SENDFLOWRESULT,

	SERVICEEXIST, SERVICEEXISTRESULT,
	// userManage
	USEREXISTRESULT, USEREXIST, LOGIN, REGISTER, AUTHORITY, CHANGEPSW, DELETEUSER, LOGINRESULT, REGISTERRESULT, AUTHORITYRESULT, CHANGEPSWRESULT, DELETEUSERRESULT, SERVICEMESSAGE, MESSAGELIST, MESSAGELISTRESULT, NODEEXAMINE, NODERECOVER, FILTERLIST, FILTERLISTSIZE, SERVICEADDRESSINFO, AUTOSERVICE, dkyserviceinfo, dkyservicelist, ServiceCallStatistics, ServiceDelayStatistics, SENDADAPTERPARAM, SENDADAPTEROK, SENDADAPTERFILE, AddServiceComp, AddTransformer, AddTransformerResult, ServicesDep, compileFile, Node_ServiceInfo, SingleServiceStatus, ServiceInterface, AllProtocol, AProtocol, sendXMLMessage, sendFlowMessage, StartServerFlow, StartFlowInfo, Modify, SingleServiceType,
	ServiceStatus,//向服务管理后台显示服务状态的消息头
	ServiceStatusResult, ServicePointType, flowListServlet, flowdetailServlet, NodeListTemplate, NodeAdd, DestroyVirtualMathine, DestroyVirtualMathineResult,//从服务管理后台接收到服务状态的消息头
	SaveNodeStrategy,SaveNodeStrategyResult,UpdateNodeStrategy,UpdateNodeStrategyResult,DeleteNodeStrategy,DeleteNodeStrategyResult,GetNodeStrategy,GetNodeStrategyResult,
	GetMonitorInfoMessage,GetMonitorInfoMessageResult, CreateVirtualMathine, CreateVirtualMathineResult, SaveVirtualMathineConfiguration, SaveVirtualMathineConfigurationResult, SendNodeLoadInfoNotice, SendNodeLoadInfoNoticeResult,
	
}

