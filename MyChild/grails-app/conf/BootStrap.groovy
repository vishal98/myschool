
import java.sql.Timestamp;
import java.util.Date;




import ghumover2.*;
import grails.converters.JSON
import groovy.sql.Sql



class BootStrap {

	private static final String ROLE_TEACHER = 'ROLE_TEACHER'
	private static final String ROLE_PARENT = 'ROLE_PARENT'

	
	def dataSource
	def init = { servletContext ->

		
		// BOOTSTRAPING DATES
		def createQuery = "CREATE TABLE IF NOT EXISTS ints ( i tinyint unique );"
		def insertQuery = "INSERT INTO ints (i) VALUES (0),(1),(2),(3),(4),(5),(6),(7),(8),(9)   ON DUPLICATE KEY UPDATE i = VALUES(i);"
		def insertCalenderDates = """\

										INSERT INTO calendar_date (calendar_date)
										SELECT DATE('2010-01-01') + INTERVAL a.i*10000 + b.i*1000 + c.i*100 + d.i*10 + e.i DAY
										FROM ints a JOIN ints b JOIN ints c JOIN ints d JOIN ints e
										WHERE (a.i*10000 + b.i*1000 + c.i*100 + d.i*10 + e.i) <= 11322
										ORDER BY 1;
                                    """
		def updateCalenderDates = """\
															UPDATE calendar_date
															SET is_weekday = CASE WHEN dayofweek(calendar_date) IN (1,7) THEN 0 ELSE 1 END,
															is_holiday = 0,
															is_payday = 0,
															year = YEAR(calendar_date),
															quarter = quarter(calendar_date),
															month = MONTH(calendar_date),
															day_of_month = dayofmonth(calendar_date),
															day_of_week = dayofweek(calendar_date),
															month_name = monthname(calendar_date),
															day_name = dayname(calendar_date),
															week_of_year = week(calendar_date),
															holiday_description = '';
"""

		def sql = new Sql(dataSource)

		sql.executeUpdate(createQuery)
		sql.executeUpdate(insertQuery)

		sql.executeUpdate(insertCalenderDates)
		sql.executeUpdate(updateCalenderDates)

		//ADDED AUGUST 15 AS HOLIDAY
				CalendarDate.executeUpdate("update CalendarDate c set c.holiday_description='Independance Day' , is_holiday = true " +
				"where c.month=8 and c.day_of_month = 15")


		// END OF BOOTSTRAPING DATES

		
		
		Role roleTeacher;
		Role roleParent;
		Teacher teacher;
		Father parent;


		roleTeacher = new Role(authority: ROLE_TEACHER)
		roleTeacher.save()

		roleParent = new Role(authority: ROLE_PARENT)
		roleParent.save()




		teacher = new Teacher(username: 'test_teacher', password: "123" , teacherId:"100" , teacherName:"John" , teacherPhoto:"100.jpg",teacherEmailId:"vis@123",phoneNo:"9634444")
		teacher.save()
		new UserRole(user:teacher , role:roleTeacher).save()








		/*
		 ADD 4 SAMPLE GRADES 5A,5B,10A AND 10B
		 */
		new Grade(grade:5 , section:"A").save(flush:true)
		new Grade(grade:5 , section:"B").save(flush:true)
		new Grade(grade:10 , section:"A").save(flush:true)
		new Grade(grade:10 , section:"B").save(flush:true)
		new Grade(grade:6 , section:"A").save(flush:true)
		new Grade(grade:6 , section:"B").save(flush:true)
		new Grade(grade:7 , section:"A").save(flush:true)
		new Grade(grade:7 , section:"B").save(flush:true)
		/*
		 Add 3 teacher entries
		 */
		new Teacher(username: 'mathew', password: "123" ,teacherId:101 , teacherName:"Mathew" , teacherPhoto:"100.jpg",teacherEmailId:"vis@123",phoneNo:"9634444").save(flush:true)
		new Teacher(username: 'sibi', password: "123" ,teacherId:102 , teacherName:"Sibi" , teacherPhoto:"101.jpg",teacherEmailId:"vis@123",phoneNo:"9634444").save(flush:true)
		new Teacher(username: 'satheesh', password: "123" ,teacherId:103 , teacherName:"Satheesh" , teacherPhoto:"102.jpg",teacherEmailId:"vis@123",phoneNo:"9634444").save(flush:true)



		/*
		 Add 2 student entries and a parent entry ,  assing 2 students to that parent
		 */
		def cl5A = Grade.get(1)
		def cl5B = Grade.get(2)
		def cl6A = Grade.get(5)
		def cl6B = Grade.get(6)
		def cl7A = Grade.get(7)
		def cl7B = Grade.get(8)
		def cl10A = Grade.get(3)
		def cl10B = Grade.get(4)

		new Student(studentName:"Rohith", grade:cl5A ,studentPhoto:"100.jpg").save(flush:true)
		new Student(studentName:"Rahul",grade:cl5B,studentPhoto:"101.jpg").save(flush:true)
		new Student(studentName:"Manoj", grade:cl10A ,studentPhoto:"102.jpg").save(flush:true)
		new Student(studentName:"Suresh",grade:cl5B,studentPhoto:"103.jpg").save(flush:true)
		new Student(studentName:"Noble", grade:cl7A ,studentPhoto:"104.jpg").save(flush:true)
		new Student(studentName:"Thomas",grade:cl6A,studentPhoto:"105.jpg").save(flush:true)

		def rohith = Student.get(1)
		def rahul = Student.get(2)
		def manoj = Student.get(3)
		def suresh = Student.get(4)
		def noble = Student.get(5)
		def thomas = Student.get(6)

		new Father(username: 'ravi', password: "123" ,parentName:"Ravi")
		.addToChildren(rohith)
		.addToChildren(rahul).save(flush:true)
		new Father(username: 'hari', password: "123" ,parentName:"Hari").save(flush:true)


		/* Add  teachers to class 5A and also set one of the teacher as classteacher */
		roleParent = Role.findByAuthority('ROLE_PARENT')
		roleTeacher = Role.findByAuthority('ROLE_TEACHER')

		def mathew = Teacher.findByTeacherId(100)
		def sibi = Teacher.findByTeacherId(101)
		def sathees = Teacher.findByTeacherId(102)

		new UserRole(user:mathew , role:roleTeacher).save(flush:true)
		new UserRole(user:sibi , role:roleTeacher).save(flush:true)
		new UserRole(user:sathees , role:roleTeacher).save(flush:true)
		new UserRole(user:Father.findByUsername('ravi') , role:roleParent).save(flush:true)
		new UserRole(user:Father.findByUsername('hari') , role:roleParent ).save(flush:true)


		cl5A.addToTeachers(mathew)

		cl5A.addToTeachers(sibi)
		cl5A.classTeacherId = mathew.id
		cl5A.classTeacherName = mathew.teacherName

		cl5A.save(flush:true)



		/*
		 Add some subjects and assing them to grades
		 */
		new Subject(subjectId:100 ,subjectName:"English").save(flush:true)
		new Subject(subjectId:101 ,subjectName:"Hindi").save(flush:true)
		new Subject(subjectId:102 ,subjectName:"Physics").save(flush:true)
		new Subject(subjectId:103 ,subjectName:"Chemistry").save(flush:true)
		new Subject(subjectId:104 ,subjectName: "Mathematics" ).save(flush: true)
		new Subject(subjectId:105 ,subjectName: "ComputerScience").save(flush: true)
		new Subject(subjectId:106 ,subjectName: "History").save(flush: true)


		def english = Subject.get(1)
		def hindi = Subject.get(2)
		def physics = Subject.get(3)
		def chemistry = Subject.get(4)
		def maths = Subject.get(5)
		def computerScience = Subject.get(6)
		def history = Subject.get(7)


		new GradeSubject(grade:5 ,subject:english).save(flush:true)
		new GradeSubject(grade:5 ,subject:maths).save(flush:true)
		new GradeSubject(grade:5 ,subject:hindi).save(flush:true)
		new GradeSubject(grade:5 ,subject:history).save(flush:true)

		new GradeSubject(grade:6 , subject:english).save(flush:true)
		new GradeSubject(grade:6 , subject:hindi).save(flush:true)
		new GradeSubject(grade:6 , subject:computerScience).save(flush:true)
		new GradeSubject(grade:6 , subject:history).save(flush:true)

		new GradeSubject(grade:7 , subject:english).save(flush:true)
		new GradeSubject(grade:7 , subject:hindi).save(flush:true)
		new GradeSubject(grade:7 , subject:computerScience).save(flush:true)
		new GradeSubject(grade:7 , subject:history).save(flush:true)



		new GradeSubject(grade:10 ,subject:physics).save(flush:true)
		new GradeSubject(grade:10 , subject:chemistry).save(flush:true);
		new GradeSubject(grade:10 , subject: history ).save(flush: true)
		new GradeSubject(grade:10 , subject: english ).save(flush: true)
		new GradeSubject(grade:10 , subject: computerScience ).save(flush: true)
		new GradeSubject(grade:10 , subject: maths ).save(flush: true)
		
		
		new  Message ( value:"homeWork" ,type:"msg",code:"hw").save(flush:true)
		new  Message ( value:"notes" ,type:"msg",code:"nt").save(flush:true)
		new  Message ( value:"project" ,type:"msg",code:"proj").save(flush:true)
				
		/*

		Add entries for home work

		 */



		//Homeworks for students
		
		   new Homework(homeworkId: 100 ,grade: cl5A , subject: "english" , homework: "English homework", dueDate: "15-04-2015" ,section: "A" ,student: rohith , message: "English Homework for Rohith , 5 A ", gradeFlag: '0').save(flush: true)
		   new Homework(homeworkId: 101 ,grade: cl5B , subject: "english" , homework: "English homework" ,  dueDate: "10-04-2015" ,section: "B", student: rahul , message: "English Homework for Rahul ,  5 B  ", gradeFlag: '0').save(flush: true)
		   new Homework(homeworkId: 102 ,grade: cl6A , subject: "history" , homework: "History homework" ,dueDate: "9-04-2015" ,section: "A", student: thomas, message: "History Homework for Thomas , 6 A  ", gradeFlag: '0').save(flush: true)
		   new Homework(homeworkId: 103 ,grade: cl6B , subject: "computerScience" , homework: "ComputerScience homework" , dueDate: "8-04-2015" ,section: "A", student: noble , message: "ComputerScience Homework for noble , 7 A ", gradeFlag: '0').save(flush: true)
		   new Homework(homeworkId: 104 ,grade: cl10A , subject: "physics" ,homework: "Physics homework", dueDate: "7-04-2015" ,section: "A",student: manoj , message: "Physics Homework for manoj 10 A", gradeFlag: '0').save(flush: true)
	  
	  
	  
	  //Homeworks for whole batch
	   
			  new Homework(homeworkId: 105 ,grade: cl5A , subject: "english" ,homework: "English homework", dueDate: "10-04-2015" ,section: "A" ,  message: "English Homework for whole 5 A Students ", gradeFlag: '1').save(flush: true)
			  new Homework(homeworkId: 106 ,grade: cl5B , subject: "english" ,homework: "English homework", dueDate: "9-04-2015" ,section: "B",  message: "English Homework for whole 5 B Students ", gradeFlag: '1').save(flush: true)
			  new Homework(homeworkId: 107 ,grade: cl6A , subject: "history" ,homework: "history homework", dueDate: "8-04-2015" ,section: "A" ,  message: "History Homework for whole 6 A Students ", gradeFlag: '1').save(flush: true)
			  new Homework(homeworkId: 108 ,grade: cl7A , subject: "computerScience" ,homework: "computerScience homework", dueDate: "7-04-2015" ,section:"A",  message: "Computer Science Homework for whole 7 A Students ", gradeFlag: '1').save(flush: true)
			  new Homework(homeworkId: 109 ,grade: cl10A , subject: "physics" ,homework: "Physics homework", dueDate: "6-04-2015" ,section: "A",  message: "Physics Homework for whole 10 A Students ", gradeFlag: '1').save(flush: true)
			  new Homework(homeworkId: 110 ,grade: cl10B , subject: "physics" ,homework: "Physics homework", dueDate: "5-04-2015" ,section: "B",  message: "Chemistry Homework for whole 10 B Students ", gradeFlag: '1').save(flush: true)
	  
	  
			  
		  //	End of homework entries
			   
	  
	  // Add exam entries
	  
			   new Exam(examId: 100 , examName: "English" , examType: "Class test").save(flush: true)
			  new Exam(examId: 101 , examName: "Chemistry" , examType: "Class test").save(flush: true)
			  new Exam(examId: 102 , examName: "Physics" , examType: "Model Exam").save(flush: true)
			  new Exam(examId: 103 , examName: "Mathematics" , examType: "Model Exam").save(flush: true)
			  new Exam(examId: 104 , examName: "Hindi" , examType: "ModelExam").save(flush: true)
			  new Exam(examId: 105 , examName: "History", examType: "Mid Term Exam").save(flush: true)
			  new Exam(examId: 106 , examName: "Computer Science", examType: "Mid Term Exam").save(flush: true)
	  
			  def exam1 , exam2 ,exam3 ,exam4 ,exam5,exam6,exam7
			  exam1 = Exam.get(1)
			  exam2 = Exam.get(2)
			  exam3 = Exam.get(3)
			  exam4 = Exam.get(4)
			  exam5 = Exam.get(5)
			  exam6 = Exam.get(6)
			  exam7 = Exam.get(7)
	  
	  
			  new ExamSchedule(exam: exam1  , subject: english ,teacher :sibi).save(flush: true)
			  new ExamSchedule(exam: exam2  , subject: chemistry ,teacher :mathew).save(flush: true)
			  new ExamSchedule(exam: exam3 , subject: physics ,teacher :sathees).save(flush: true)
			  new ExamSchedule(exam: exam4, subject: maths ,teacher : sibi).save(flush: true)
			  new ExamSchedule(exam: exam5, subject: hindi ,teacher :mathew).save(flush: true)
			  new ExamSchedule(exam: exam6 , subject: history ,teacher :sathees).save(flush: true)
			  new ExamSchedule(exam: exam7 , subject: computerScience ,teacher :sibi).save(flush: true)
	  
			  new ExamSyllabus(exam: exam1 , subject: english ,syllabus: "English Syllabus" ).save(flush: true)
			  new ExamSyllabus(exam: exam2 , subject: chemistry,syllabus: "Chemistry Syllabus").save(flush: true)
			  new ExamSyllabus(exam: exam3 , subject: physics , syllabus: "Physics Syllabus").save(flush: true)
	  
	  
	  



		JSON.createNamedConfig('thin') {
			it.registerObjectMarshaller( Grade ) { Grade grade ->

				def output = [:]
				output['grade'] = grade.name
				output['section'] = grade.section

				return output
			}
		}
		
		JSON.registerObjectMarshaller( Grade ) { Grade grade ->
			
							def output = [:]
							output['grade'] = grade.name
							output['section'] = grade.section
			
							return output
						}

		JSON.createNamedConfig('homework') {
			it.registerObjectMarshaller( Homework ) { Homework home ->
			
				

				def output = [:]
				output['subject'] = home.subject
				output['dueDate'] = home.dueDate
				output['homeGivenDate'] = home.dateCreated
				output['details'] = home.message

				return output
			}
		}

		JSON.createNamedConfig('gradesubject') {
			it.registerObjectMarshaller( GradeSubject ) { GradeSubject gradeSubject ->

				def output = [:]
				output['gradeId'] = gradeSubject1.gradeId
				output['subjectId'] = gradeSubject1.subjectId

				return output
			}
		}
		JSON.createNamedConfig('student') {
			it.registerObjectMarshaller( Student ) { Student student ->

				def output = [:]
				output['studentId'] = student.studentId
				output['studentName'] = student.studentName

				return output
			}
		}
	
			JSON.registerObjectMarshaller( Father ) { Father father ->

			
					 return ['parentId': father.id,
				'parentName': father.parentName,
				'emailId':father.emailId, student:father.children]
			}
		
		
		JSON.registerObjectMarshaller(Student) {
			 Student student ->

				def output = [:]
				output['studentId'] = student.studentId
				output['studentName'] = student.studentName
				output['grade']=student.grade.name
				output['section']=student.grade.section
				output['classTeacherName']=student.grade.classTeacherName
				
				

				return output
			
		}
		
		JSON.createNamedConfig('msg') {
			it.registerObjectMarshaller( Message ) { Message msg ->
			
					 return ['code': msg.code,
				'value': msg.value]
			}
		}
		
		JSON.createNamedConfig('teacherC') {
			it.registerObjectMarshaller( Teacher ) { Teacher teach ->

			

				 return  ['teacher':['teacherId': teach.teacherId,
				'teacherName': teach.teacherName,
				'emailId':teach.teacherEmailId, grades:teach.grades]]
			}
		}
		JSON.createNamedConfig('Success') {
			it.registerObjectMarshaller( Success ) { Success success ->

				def output = [:]
				output['success'] = 0
				output['failure'] = 1


				return output
			}
		}
	}

	def destroy = {
	}
}