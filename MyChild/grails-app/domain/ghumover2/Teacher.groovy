package ghumover2
import grails.rest.Resource

@Resource
class Teacher  extends User  {

     static belongsTo = Grade
     static hasMany = [grades:Grade]

     int teacherId
	 String teacherName
	 String teacherPhoto
     
     

     static constraints = {
    	
          
    }
}
