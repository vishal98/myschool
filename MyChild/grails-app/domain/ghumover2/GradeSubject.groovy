package ghumover2
import grails.rest.Resource

@Resource
class GradeSubject {

    int grade
	Subject subject
    static constraints = {
    }
}
