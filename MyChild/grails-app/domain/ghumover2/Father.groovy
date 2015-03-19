package ghumover2
import grails.rest.Resource

@Resource
class Father extends User {
   
    //int parentId
	String parentName
	String emailId
    static hasMany = [children:Student]

    static constraints = {
		 emailId(nullable:true)
		
    }
}
