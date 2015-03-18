package ghumover2
import grails.rest.Resource

@Resource
class Father extends User {
   
    //int parentId
	String parentName
    static hasMany = [children:Student]

    static constraints = {
		
    }
}
