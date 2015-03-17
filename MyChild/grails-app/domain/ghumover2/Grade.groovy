package ghumover2
import grails.rest.Resource

@Resource
class Grade {

    static hasMany = [teachers:Teacher , students:Student]
   
    int grade
	String section
    int classTeacherId 
 



    static constraints = {
    	classTeacherId(nullable:true)
    }
}
