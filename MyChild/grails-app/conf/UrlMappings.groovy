class UrlMappings {

	static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/"(view:"/index")
        "500"(view:'/error')
		
	/*	"/detail/$name/event/$event?" {
			controller = "ghumo"
			action = "showEvents"
		
		}
			"/user/name/$name/email/$email/message/$msg"{
				controller = "ghumo"
				action = "addMessage"
		}
			
			"/blogList/$msg?"{
				controller = "blog"
				action = "getBlogs"
		}
			
			"/name/$name"{
				controller = "teacher"
				action = "getGrade"
		}
			"/homework/"{
				controller = "teacher"
				action = "getHomeWork"
		}
			"/subject/$grade"{
				controller = "teacher"
				action = "getSubject"
		}
			"/Teacher/studentList/$gradeId"{
				controller = "teacher"
				action = "getStudentList"
		}
			
			"/Teacher/sendmessage/"{
				controller = "teacher"
				action = "sendMessage"
		}
			"/accountinfo/$id"{
				controller = "parent"
				action = "accountInfo"
		}
				"/Parent/gethomework/$userId"{
					controller = "parent"
					action = "getHomeWork"
			}
			"/gettodayhomework/$id"{
				controller = "parent"
				action = "getTodayHomeWork"
		}
			
			"/Parent/username/$username"{
				controller = "parent"
				action = "getParentDetails"
		}
			
			"/activity/$activityCode"{
				controller = "ghumo"
				action = "getActivityDetail"
		}
			
			"/Teacher/id/$userId"{
				controller = "teacher"
				action = "getTeacherDetails"
		}
			
			"/Parent/studentId/$stdid/teacher/$classid"{
				controller = "parent"
				action = "getHomeWork"
		}
			"/Teacher/msg"{
				controller = "teacher"
				action = "getMsg"
		}
			"/Parent/exam/$classid"{
				controller = "parent"
				action = "getExamDetails"
		}
			"/Parent/child/schedule/$examId"{
				controller = "teacher"
				action = "getExamSchedule"
		}
			"/Parent/child/syllabus/$examId/$scheduleId"{
				controller = "teacher"
				action = "getSyllabus"
		}
			"/Parent/student"(resources:'student') {
				"/father"(resources:'guardian')
			}
	
			"/Parent/guardian"(resources: 'guardian')
					{
	
					}

*/

		// NEW ADDED APIS


		"/myapp/timetable/$gradeId"
				{
			controller = "TimeTable"
			action = "getWeekTimetable"

		     }

		"/myapp/timetable/$gradeId/$section/$day"
				{
					controller = "TimeTable"
					action =  "getDayTimeTable"
				}




        "/myapp/parent/accountInfo/$id"
				{
					controller = "Guardian"
					action = "getAccountInfo"
				}

		"/myapp/getHomework/$gradeId/$section"
				{
					controller = "Homework"
					action = "getClassHomework"

				}
		"/myapp/getHomework/$gradeId/$section/$subject"
				{
					controller = "Homework"
					action = "getClassHomeworkBySubject"

				}

		"/myapp/teacher/homework"(resources:"Homework")


		"/myapp/parent"(resources : "guardian")

		"/myapp/student"(resources : "student")









	}
			 
			
	
}
