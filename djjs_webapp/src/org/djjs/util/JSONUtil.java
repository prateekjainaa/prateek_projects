/**
 * Jun 15, 2009
 * @author Prateek Jain
 */
package org.djjs.util;

import java.util.Iterator;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class JSONUtil {
    private static JSONUtil jSONUtil = null;
    private JSONUtil(){}
    
    public static JSONUtil getInstance() {
	if(null != jSONUtil) {
	    return jSONUtil;
	}
	jSONUtil = new JSONUtil();
	return jSONUtil;
    }
    
    public String convertJavaObjectToJsonString(Object[] objectToConvert) {
	JSONObject jsonObject=new JSONObject();
	JSONArray jsonArr=makeJSONArray(objectToConvert);
	jsonArr.add(objectToConvert);
	jsonObject.put("success",true);
	jsonObject.put("rows",jsonArr);
	//JSONObject jsonObject=JSONObject.fromObject(objectToConvert);
	String retValue = jsonObject.toString();
	jsonObject=null;
	jsonArr=null;
	return retValue;
}
    
    private JSONArray makeJSONArray(Object[] obj) {
	JSONArray jsonArray = new JSONArray();
	JSONObject jsonObject = null;
	for(Object temp : obj) {
	    jsonObject = new JSONObject();
	    jsonObject = JSONObject.fromObject(temp);
	    jsonArray.add(jsonObject);
	}
	return jsonArray;
    }
    
    /*public static String convertJavaObjectToJsonString(Object objectToConvert) {
	JSONObject jsonObject=new JSONObject();
	JSONArray jsonArr=new JSONArray();
	jsonArr.add(objectToConvert);
	jsonObject.put("success",true);
	jsonObject.put("rows",jsonArr);
	//JSONObject jsonObject=JSONObject.fromObject(objectToConvert);
	String retValue = jsonObject.toString();
	jsonObject=null;
	jsonArr=null;
	return retValue;
}*/
    public String convertJavaObjectToComboBoxJsonString(List listToConvert){
	JSONObject jsonObject=new JSONObject();
	JSONArray jsonArr=new JSONArray();
	if(listToConvert!=null){
		Iterator listToConvertItr=listToConvert.iterator();
		while(listToConvertItr.hasNext()){
			JSONObject tempJsonObj=new JSONObject();
			tempJsonObj=JSONObject.fromObject(listToConvertItr.next());
			jsonArr.add(tempJsonObj);
		}
	}
	jsonObject.put("totalCount",jsonArr.size());
	jsonObject.put("rows",jsonArr);
	String retValue = jsonObject.toString();
	jsonObject=null;
	jsonArr=null;
	return retValue;
}
    
}
