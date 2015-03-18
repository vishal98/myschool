class UrlMappings {

	static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/"(view:"/index")
        "500"(view:'/error')
		
		"/detail/$name/event/$event?" {
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
			"/studentList/$grade/$studentSection"{
				controller = "teacher"
				action = "getStudentList"
		}
			
			"/sendmessage/"{
				controller = "teacher"
				action = "sendMessage"
		}
			"/accountinfo/$id"{
				controller = "parent"
				action = "accountInfo"
		}
				"/Parent/gethomework/$id"{
					controller = "parent"
					action = "getHomeWork"
			}
			"/gettodayhomework/$id"{
				controller = "parent"
				action = "getTodayHomeWork"
		}
			
			"/activity/$activityCode"{
				controller = "ghumo"
				action = "getActivityDetail"
		}
	}
}
