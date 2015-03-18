
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

		/*
		 Add 3 teacher entries
		 */
		new Teacher(username: 'mathew', password: "123" ,teacherId:101 , teacherName:"Mathew" , teacherPhoto:"100.jpg",teacherEmailId:"vis@123",phoneNo:"9634444").save(flush:true)
		new Teacher(username: 'sibi', password: "123" ,teacherId:102 , teacherName:"Sibi" , teacherPhoto:"101.jpg",teacherEmailId:"vis@123",phoneNo:"9634444").save(flush:true)
		new Teacher(username: 'satheesh', password: "123" ,teacherId:103 , teacherName:"Satheesh" , teacherPhoto:"102.jpg",teacherEmailId:"vis@123",phoneNo:"9634444").save(flush:true)



		/*
		 Add 2 student entries and a parent entry ,  assing 2 students to that parent
		 */
		def cl1 = Grade.get(1)
		def cl2 = Grade.get(3)

		new Student(studentId:100,studentName:"Rohith", grade:cl1 ,studentPhoto:"100.jpg").save(flush:true)
		new Student(studentId:101,studentName:"Rahul",grade:cl2,studentPhoto:"101.jpg").save(flush:true)
		def rohith = Student.get(1)
		def rahul = Student.get(2)

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


		cl1.addToTeachers(mathew)

		cl1.addToTeachers(sibi)
		cl1.classTeacherId = mathew.id

		cl1.save(flush:true)



		/*
		 Add some subjects and assing them to grades
		 */
		new Subject(subjectId:100 ,subjectName:"English").save(flush:true)
		new Subject(subjectId:101 ,subjectName:"Hindi").save(flush:true)
		new Subject(subjectId:102 ,subjectName:"Physics").save(flush:true)
		new Subject(subjectId:103 ,subjectName:"Chemistry").save(flush:true)


		def english = Subject.get(1)
		def hindi = Subject.get(2)
		def physics = Subject.get(3)
		def chemistry = Subject.get(4)

		new GradeSubject(grade:5 ,subject:english).save(flush:true)
		new GradeSubject(grade:5 , subject:hindi).save(flush:true)
		new GradeSubject(grade:10 ,subject:physics).save(flush:true)
		new GradeSubject(grade:10 , subject:chemistry).save(flush:true);


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