package ghumover2
import grails.rest.Resource

@Resource
class Parent extends User {
   
    //int parentId
	String parentName
    static hasMany = [children:Student]

    static constraints = {
    }
}
