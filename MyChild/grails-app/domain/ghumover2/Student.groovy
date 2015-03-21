package ghumover2
import grails.rest.Resource

@Resource
class Student {
    
     Long studentId
	 String studentName  
	 // int studentAge // should be dob
	 // int studentGrade
 	 // String studentSection
 	 static belongsTo = [grade:Grade ,parent:Father]
	 String studentPhoto
	
	 static mapping = {
	 id generator: 'increment',name: 'studentId'
     }
    static constraints = {
		
    	parent(nullable:true)
    	grade(nullable:true)
    }
}
