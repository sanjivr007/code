package controller;

import static org.hamcrest.CoreMatchers.instanceOf;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import processor.EquationTree;

public class ParseController {
	
	public static void main(String[] args){
		JSONParser parser = new JSONParser();
		try{
		Object object = parser.parse(new FileReader("/home/sanjiv/sample.json"));
        
        //convert Object to JSONObject
        JSONObject jsonObject = (JSONObject)object;
        
        /*//Reading the String
        String name = (String) jsonObject.get("Name");
        Long age = (Long) jsonObject.get("Age");*/
       // String a = (String)jsonObject.get("op");
        EquationTree et = new EquationTree();
        et.tree(jsonObject);
        
       
        /*Object op = jsonObject.get("lhs");
        System.out.println(op.getClass());
        if(op instanceof JSONObject ){
        	System.out.println(op);
        }
        */
        
		}
		catch(Exception e){
			System.out.println("NOt able to parse data");
			e.printStackTrace();
		}
	}

}
