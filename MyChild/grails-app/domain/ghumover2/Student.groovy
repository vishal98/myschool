package ghumover2
import grails.rest.Resource

@Resource
class Student {
    
     int studentId
	 String studentName  
	 // int studentAge // should be dob
	 // int studentGrade
 	 // String studentSection
 	 static belongsTo = [grade:Grade ,parent:Parent]
	 String studentPhoto
	


    static constraints = {

    	parent(nullable:true)
    	grade(nullable:true)
    }
}
