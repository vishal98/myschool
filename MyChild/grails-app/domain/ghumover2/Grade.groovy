package ghumover2
import grails.rest.Resource

@Resource
class Grade {

    static hasMany = [teachers:Teacher , students:Student]
    Long gradeId
    int name
	String section
    int classTeacherId 
	String classTeacherName
 

static mapping ={
	id generator: 'increment',name: 'gradeId'
    }

    static constraints = {

    	classTeacherId(nullable:true)
		classTeacherName(nullable:true)
    }
}
