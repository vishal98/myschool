package ghumover2

import org.grails.databinding.BindingFormat

class Homework 
{
	int homeworkId
	Grade grade
	String subject
	@BindingFormat('dd-MM-yyyy')
	Date dueDate
	String homework
	String section
	@BindingFormat('dd-MM-yyyy')
	Date dateCreated
	Student student
	String message
	String gradeFlag
	static namedQueries = {
		todaysPosts {
		   def now = new Date().clearTime()
		   between('dateCreated', now, now+1)
		 
		}
	}
	static constraints = {
		student(nullable:true)
		grade(nullable:true)
		}
	
	
}
