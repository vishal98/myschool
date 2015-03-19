
import ghumover2.*;
import grails.converters.JSON



class BootStrap {

	private static final String ROLE_TEACHER = 'ROLE_TEACHER'
	private static final String ROLE_PARENT = 'ROLE_PARENT'


	def init = { servletContext ->

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

		new Student(studentId:100,studentName:"Rohith", grade:cl5A ,studentPhoto:"100.jpg").save(flush:true)
		new Student(studentId:101,studentName:"Rahul",grade:cl5B,studentPhoto:"101.jpg").save(flush:true)
		new Student(studentId:102,studentName:"Manoj", grade:cl10A ,studentPhoto:"102.jpg").save(flush:true)
		new Student(studentId:103,studentName:"Suresh",grade:cl5B,studentPhoto:"103.jpg").save(flush:true)
		new Student(studentId:104,studentName:"Noble", grade:cl7A ,studentPhoto:"104.jpg").save(flush:true)
		new Student(studentId:105,studentName:"Thomas",grade:cl6A,studentPhoto:"105.jpg").save(flush:true)

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
		/*

		Add entries for home work

		 */


 /*
 Homeworks for students
  */
	 new Homework(homeworkId: 100 ,grade: cl5A , subject: "english" , homework: "English homework", dueDate: "15-04-2015" ,section: "A" ,student: rohith , message: "English Homework for Rohith , 5 A ", gradeFlag: '0').save(flush: true)
	 new Homework(homeworkId: 101 ,grade: cl5B , subject: "english" , homework: "English homework" ,  dueDate: "10-04-2015" ,section: "B", student: rahul , message: "English Homework for Rahul ,  5 B  ", gradeFlag: '0').save(flush: true)
	 new Homework(homeworkId: 102 ,grade: cl6A , subject: "history" , homework: "History homework" ,dueDate: "9-04-2015" ,section: "A", student: thomas, message: "History Homework for Thomas , 6 A  ", gradeFlag: '0').save(flush: true)
	 new Homework(homeworkId: 103 ,grade: cl6B , subject: "computerScience" , homework: "ComputerScience homework" , dueDate: "8-04-2015" ,section: "A", student: noble , message: "ComputerScience Homework for noble , 7 A ", gradeFlag: '0').save(flush: true)
	 new Homework(homeworkId: 104 ,grade: cl10A , subject: "physics" ,homework: "Physics homework", dueDate: "7-04-2015" ,section: "A",student: manoj , message: "Physics Homework for manoj 10 A", gradeFlag: '0').save(flush: true)


/*
Homeworks for whole batch
 */
		new Homework(homeworkId: 105 ,grade: cl5A , subject: "english" ,homework: "English homework", dueDate: "10-04-2015" ,section: "A" ,  message: "English Homework for whole 5 A Students ", gradeFlag: '1').save(flush: true)
		new Homework(homeworkId: 106 ,grade: cl5B , subject: "english" ,homework: "English homework", dueDate: "9-04-2015" ,section: "B",  message: "English Homework for whole 5 B Students ", gradeFlag: '1').save(flush: true)
		new Homework(homeworkId: 107 ,grade: cl6A , subject: "history" ,homework: "history homework", dueDate: "8-04-2015" ,section: "A" ,  message: "History Homework for whole 6 A Students ", gradeFlag: '1').save(flush: true)
		new Homework(homeworkId: 108 ,grade: cl7A , subject: "computerScience" ,homework: "computerScience homework", dueDate: "7-04-2015" ,section:"A",  message: "Computer Science Homework for whole 7 A Students ", gradeFlag: '1').save(flush: true)
		new Homework(homeworkId: 109 ,grade: cl10A , subject: "physics" ,homework: "Physics homework", dueDate: "6-04-2015" ,section: "A",  message: "Physics Homework for whole 10 A Students ", gradeFlag: '1').save(flush: true)
		new Homework(homeworkId: 110 ,grade: cl10B , subject: "physics" ,homework: "Physics homework", dueDate: "5-04-2015" ,section: "B",  message: "Chemistry Homework for whole 10 B Students ", gradeFlag: '1').save(flush: true)


		/*
		End of homework entries
		 */





		JSON.createNamedConfig('thin') {
			it.registerObjectMarshaller( Grade ) { Grade grade ->

				def output = [:]
				output['grade'] = grade.grade
				output['section'] = grade.section

				return output
			}
		}

		JSON.createNamedConfig('homework') {
			it.registerObjectMarshaller( Homework ) { Homework home ->

				def output = [:]
				output['homeworkId'] = home.homeworkId
				output['homework'] = home.homework

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
		JSON.createNamedConfig('father') {
			it.registerObjectMarshaller( Father ) { Father father ->

			
					 return ['parentId': father.parentId,
				'parentName': father.parentName,
				'emailId':teach.emailId, children:father.children]
			}
		}
		
		JSON.createNamedConfig('teacherC') {
			it.registerObjectMarshaller( Teacher ) { Teacher teach ->

			

				 return ['teacherId': teach.teacherId,
				'teacherName': teach.teacherName,
				'emailId':teach.teacherEmailId, grades:teach.grades]
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