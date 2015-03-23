package ghumover2

import grails.converters.JSON
import grails.rest.RestfulController
import grails.plugin.springsecurity.annotation.Secured;

//@Secured(['ROLE_TEACHER','ROLE_PARENT'])
class HomeworkController extends RestfulController
{

    static responseFormats = ['json', 'xml']

    HomeworkController() {
        super(Homework)
    }


   def getClassHomework()
   {
            def gradeName = params.gradeId
            def section = params.section
            def grade = Grade.findByNameAndSection(gradeName,section)
            def response = Homework.findAllByGrade(grade)
            render response as JSON

   }

   def getClassHomeworkBySubject()
   {
       def gradeName = params.gradeId
       def section = params.section
       def subject = params.subject
       def grade = Grade.findByNameAndSection(gradeName,section)
       def response = Homework.findAllByGradeAndSubject(grade,subject)
       render response as JSON
   }

}