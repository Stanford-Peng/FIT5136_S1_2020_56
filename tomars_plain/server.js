const express = require('express');
const path = require("path");
const handlebars = require("express-handlebars");
const bodyParser = require("body-parser");
const fs = require("fs");
const readline = require("readline");
//const multer = require('multer');
var session = require("express-session");
const app = express();
const axios = require("axios");

app.use("/public", express.static(path.join(__dirname, "public")));
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: true }));
app.use(bodyParser.raw());

app.engine("handlebars", handlebars());
app.set("view engine", 'handlebars');
app.set('views', path.join(__dirname, 'views'));





app.use(session({
    secret: "secret code",
    resave: true,
    saveUninitialized: true
}))
//app.use(require('flash')());
// let axiosDefaults = require('axios/lib/defaults');
// axiosDefaults.baseURL = 'http://locahost:8080/';
var hbs = handlebars.create({});
// express.urlencoded([extended]);
var limitLength = function (content, maxLength) {
    if (content.length > maxLength) {
        content = content.substring(0, maxLength) + "...";

    }
    return content;
}

var cut = function (content, length) {
    return content.substring(0, length);
}
var slash = function (content) {

    var result = "";
    if (Array.isArray(content)) {
        for (item of content) {
            result = result + item + "/ "
        }
        result = result.substring(0, result.length - 2)
    } else {
        result = result.replace(",", "/");
        return result;
    }


    return result;
};
hbs.handlebars.registerHelper("cut", cut);
hbs.handlebars.registerHelper("slash", slash);

app.get("/", function (req, res) {
    res.sendFile(path.join(__dirname, 'index.html'))
})

app.get("/administrator", function (req, res) {
    if (!req.session["admin"]) {
        res.render("administrator", { layout: false })
    } else {
        res.redirect("adminHome");
    }
})

app.get("/candidate", function (req, res) {

    res.render("candidate", { layout: false });
})

app.get("/coordinator", function (req, res) {
    if (!req.session["coordinator"]) {
        res.render("coordinator", { layout: false });
    } else {
        res.redirect("coordinatorHome");
    }
})

app.post("/adminLogin", function (req, res) {
    //console.log(req.body);
    var username = req.body['username'];
    var password = req.body['password'];
    if (username == "admin" && password == "admin") {
        req.session["admin"] = username;
        res.redirect("/adminHome");
    }
    else {

        res.send("Incorrect username or password, <a href='/administrator'>Click to go back</a> ");
        res.end();
    }
    //console.log(req.session);
    // console.log(req.session["admin"]);
})

app.post("/coordinatorLogin", function (req, res) {
    //console.log(req.body);
    var username = req.body['username'];
    var password = req.body['password'];
    if (username == "coordinator" && password == "coordinator") {
        req.session["coordinator"] = username;
        res.redirect("/coordinatorHome");
    }
    else {

        res.send("Incorrect username or password, <a href='/coordinator'>Click to go back</a> ");
        res.end();
    }
    //  console.log(req.session);
    //  console.log(req.session["id"]);
    //  console.log(req.session["coordinator"]);
})




app.get("/adminHome", function (req, res) {
    if (!req.session["admin"]) {
        res.redirect("/administrator");
    } else {
        var missions = [];
        axios.get("http://localhost:8080/api/mission").then(function (response) {
            //alert("Data: " + response + "\nStatus: ");
            //console.log(response.data);
            //response = JSON.parse(response);
            for (item of response.data) {

                //var mission=JSON.parse(item);
                console.log(item);
                missions.push(item);

            };
            res.render("adminHome", { layout: "adminBar", 'missions': missions });
            console.log("missions:" + missions);
        }).catch(error => {
            console.log(error)
        })
    }
    // console.log(req.session["admin"]);
    // console.log(req.session["coordinator"]);
    // console.log(req.session["id"]);
})


app.get("/mission/admin/:missionId", function (req, res) {
    console.log(req.params["missionName"]);
    if (!req.session["admin"]) {
        res.redirect("/administrator");
    } else {
        var mission;
        axios.get("http://localhost:8080/api/mission/" + req.params['missionId']).then(function (response) {
            console.log(response.data);
            res.render("missionAdmin", { "layout": null, "mission": response.data });
        }
        ).catch(error => {
            console.log(error);
        })
    }
}
)

app.get("/mission/:id/shuttle/", function (req, res) {
    if (!req.session["admin"]) {
        res.redirect("/administrator");
    } else {
        var missionId = req.params['id'];
        //missionId["id"] = 
        var shuttles = [];
        console.log(missionId);
        axios.get("http://localhost:8080/api/shuttle").then(function (response) {
            for (item of response.data) {
                item['missionId'] = req.params['id'];
                console.log(item);
                shuttles.push(item);

            };
            res.render("shuttleList", { layout: "adminBar", 'shuttles': shuttles, "missionId": missionId });
            console.log("shuttles:" + shuttles);
        }).catch(error => {
            console.log(error)
        })
    }
})

app.post("/check", function (req, res) {
    //console.log(req.body);
    var config = {
        headers: {
            'Content-Type': 'application/json'
        },
        responseType: 'text'
    };
    axios({
        method: 'get',
        responseType: 'text',
        url: "http://localhost:8080/api/user/hasDuplicateUsername",
        headers: {
            'Content-Type': 'application/json'
        },
        data: req.body["username"]
    }).then(function (response) {
        //console.log(response.data);
        res.json({ "exist": response.data });
    }).catch(error => {
        console.log(error)
    });


})

app.post("/signUp", function (req, res) {
    console.log(req.body);
    var body = {
        "userName": req.body.username,
        "password": req.body.password,
        "profile": null,
        "userId": -1
    }
    axios.post("http://localhost:8080/api/user", body).then(function (response) {
        if (!response.data) {
            res.json({ "success": "true" });
        }
    }).catch(function (error) {
        console.log(error);
    })

}
)

app.post("/signin", function (req, res) {
    console.log(req.body);
    var body = {
        "userName": req.body.username,
        "password": req.body.password,
        "profile": null,
        "userId": 3
    }
    axios.post("http://localhost:8080/api/user/candidateLogin", body).then(function (response) {
        //console.log(response.data);
        if (response.data !== -1) {
            req.session["candidate"] = response.data;
            //res.redirect("/candidateHome");
            res.json({ "success": "true" });

        }
        else{
            res.json({ "success": "false" });
        }
        // else {
        //     //req.flash("Fail");
        //     res.send("Password or username incorrect, <a href='/candidate'>Click to go to log in</a>");
        // }
    }).catch(function (error) {
        console.log(error);
    })

})

app.get("/candidateHome", function (req, res) {
    if (!req.session["candidate"]) {
        res.redirect("/candidate");
    } else {
        console.log(req.session["candidate"]);
        axios.get("http://localhost:8080/api/user/" + req.session["candidate"]).then(
            function (response) {
                res.render("candidateHome", { layout: null, "user": response.data });
            }
        ).catch(function (error) {
            console.log(error);
        })


    }

})

app.get("/candidateLogout", function (req, res) {
    delete req.session["candidate"];
    res.redirect("/candidate");
})

app.get("/editProfile", function (req, res) {
    if (!req.session["candidate"]) {
        res.redirect("/candidate");
    } else {
        console.log(req.session["candidate"]);
        axios.get("http://localhost:8080/api/user/" + req.session["candidate"]).then(
            function (response) {
                res.render("editProfile", { "layout": "editProfileBar", "user": response.data });
            }
        ).catch(function (error) {
            console.log(error);
        })


    }
})
app.post("/editProfile", function (req, res) {
    if (!req.session["candidate"]) {
        res.redirect("/candidate");
    } else {
        console.log(req.body);
        body = {};
        body["name"] = req.body.name;
        body["dob"] = req.body.dob;
        body["street"] = req.body.street;
        body["city"] = req.body.city;
        body["postal"] = req.body.postal;
        body["country"] = req.body.country;
        body["idNumber"] = req.body.idNumber;
        body["idType"] = req.body.idType;
        body["gender"] = req.body.gender;
        body["allergies"] = [req.body.allergies];
        body["foodPref"] = req.body.gender;
        body["qualifications"] = [req.body.qualifications];
        body["workExp"] = [req.body.workExp];
        body["occupations"] = [req.body.occupations];
        body["computerSkill"] = req.body.computerSkill;
        body["languages"] = [req.body.languages];
        axios.put("http://localhost:8080/api/user/setProfile/" + req.session["candidate"], body).then(function(response){
            if(!response.data){
                res.send("Edit successfully ,<a href='/candidateHome'>Back to home</a>");
            //res.redirect("/candidateHome");
            } else{
                res.send("Oops something wrong, <a href='/candidateHome'>Back to home</a>");
            }

        })
        .catch(function (error) {
            console.log(error);
        })


    }

})


app.get("/mission/:missionId/shuttle/:shuttleId", function (req, res) {
    console.log(req.params);
    if (!req.session["admin"]) {
        res.redirect("/administrator");
    } else {
        var shuttleId = parseFloat(req.params["shuttleId"]);
        // console.log(shuttleId);
        var config = {
            headers: {
                'Content-Type': 'application/json'
            },
            responseType: 'text'
        };
        axios.put("http://localhost:8080/api/mission/selectShuttle/" + req.params["missionId"], req.params["shuttleId"], config).then(function (response) {
            console.log("select res: " + response.data);
            if(!response.data){
            res.send("You have successfully selected the shuttle, <a href='/adminHome'>Click to go back to the home page</a>")
            }else{
                res.send("Oops something wrong, <a href='/adminHome'>Back to home</a>");
            }
        })
            .catch(function (error) {
                console.log(error);
            });
    }

})

app.get("/mission/:missionId/candidate",function(req,res){
    console.log(req.params);
    if (!req.session["admin"]) {
        res.redirect("/administrator");
    } else {
        axios.get("http://localhost:8080/api/mission/" + req.params['missionId']).then(function (response) {
            console.log(response.data);
            res.render("selectCandidate", { "layout": null, "mission": response.data})
        }).catch(error => {
            console.log(error);
        });
    }
})

app.get("/findBest/:missionId", function (req, res) {
    console.log(req.params);
    if (!req.session["admin"]) {
        res.redirect("/administrator");
    } else {
        axios.get("http://localhost:8080/api/mission/" + req.params['missionId']).then(function (response) {
            axios.post("http://localhost:8080/api/user/findBest", response.data).then(
                function(result){
                    console.log(response.data);
                    console.log(result);
                    //res.json(result);
                }
            ).catch(error => {
                console.log(error);
            });
        }).catch(error => {
            console.log(error);
        });

    }

})

app.get("/coordinatorHome", function (req, res) {
    if (!req.session["coordinator"]) {
        res.redirect("/coordinator");
    } else {
        var missions = [];
        axios.get("http://localhost:8080/api/mission").then(function (response) {
            //alert("Data: " + response + "\nStatus: ");
            console.log(response.data);
            //response = JSON.parse(response);
            for (item of response.data) {

                //var mission=JSON.parse(item);
                console.log(item);
                missions.push(item);

            };
            res.render("coordinatorHome", { layout: "coordinatorBar", 'missions': missions });
            console.log("missions:" + missions);
        }).catch(error => {
            console.log(error)
        })

    };
    // console.log(req.session["admin"]);
    // console.log(req.session["coordinator"]);
    // console.log(req.session["id"]);
})

app.get("/adminLogout", function (req, res) {
    if (req.session["admin"]) {
        delete req.session["admin"]; //not thorough
        res.redirect("/administrator");
    } else {
        res.send("You have already logged out, <a href='/administrator'>Click to go to log in</a>")
    }
})

app.get("/coordinatorLogout", function (req, res) {
    if (req.session["coordinator"]) {
        delete req.session["coordinator"]; //not thorough
        res.redirect("/coordinator");
    } else {
        res.send("You have already logged out, <a href='/coordinator'>Click to go to log in</a>")
    }
})

app.get("/createMission", function (req, res) {
    if (!req.session["coordinator"]) {
        res.redirect("/coordinator");
    } else {
        res.render("createMission", { "layout": "createMissionBar" });
    }
})

app.get("/editMission/:missionId", function (req, res) {
    if (!req.session["admin"] && !req.session["coordinator"]) {
        res.sendFile(path.join(__dirname, 'index.html'));
    } else if (req.session["admin"]) {
        axios.get("http://localhost:8080/api/mission/" + req.params['missionId']).then(function (response) {
            console.log(response.data);
            res.render("editMission", { "layout": "editMissionBar", "mission": response.data, "who": req.session["admin"] })
        }).catch(error => {
            console.log(error);
        });
    } else {
        axios.get("http://localhost:8080/api/mission/" + req.params['missionId']).then(function (response) {
            console.log(response.data);
            res.render("editMission", { "layout": "editMissionBar", "mission": response.data, "who": req.session["coordinator"] });
        }).catch(error => {
            console.log(error);
        })

    }

}
)

app.post("/putMission/:missionId", function (req, res) {
    console.log(req.body);
    if (!req.session["admin"] && !req.session["coordinator"]) {
        res.redirect("/administrator");
    } else {
        var mission;
        var jobNames = req.body.jobName;
        var jobDescription = req.body.jobDescription;
        var jobs = {};
        var countries = [];
        var candidates = [];
        if (Array.isArray(jobNames)) {
            for (i = 0; i < jobNames.length; i++) {
                jobs[jobNames[i]] = jobDescription[i].replace("\r\n", "").trim();
            }
        } else {
            jobs[jobNames] = jobDescription.replace("\r\n", "").trim();
        }
        var title = req.body.title;
        var numberOfTitle = req.body.numberOfTitle;
        var titles = {};
        if (Array.isArray(title)) {
            for (i = 0; i < title.length; i++) {
                titles[title[i]] = parseInt(numberOfTitle[i]);
            }
        } else {
            titles[title] = parseInt(numberOfTitle);
        }
        var coordinatorInfo = {};
        coordinatorInfo["name"] = req.body["name"];
        coordinatorInfo["email"] = req.body["contactEmail"];
        coordinatorInfo["phone"] = req.body["phoneNumber"];
        if (!req.body.hasOwnProperty("countriesAllowed")) {
            countries = [null];

        } else if (Array.isArray(req.body["countriesAllowed"])) {
            for (country of req.body["countriesAllowed"]) {
                countries.push(country);
            }
        } else {
            countries = [req.body["countriesAllowed"]];
        }

        if (!req.body.hasOwnProperty("candidates")) {
            candidates = [null];

        } else if (Array.isArray(req.body["candidates"])) {
            for (country of req.body["candidates"]) {
                countries.push(candidate);
            }
        } else {
            countries = [req.body["candidates"]];
        }


        mission = {
            id: req.params['missionId'],
            missionName: req.body["missionName"],
            missionDesc: req.body["missionDescription"].replace("\r\n", "").trim(),
            origin: req.body["countryOfOrigin"],
            allowedCountries: countries,
            coordinatorInfo: coordinatorInfo,
            jobs: jobs,
            launchDate: req.body["launchDate"] + 'T00:00:00.000+0000',
            duration: parseInt(req.body["missionDuration"]),
            location: req.body["location"],
            cargoFor: req.body["cargoFor"],
            empReq: titles,
            status: req.body["missionStatus"],
            candidates: candidates,
            shuttleId: req.body["shuttleId"]
        };
        axios.put("http://localhost:8080/api/mission/" + req.params['missionId'], mission).then(function (response) {
            console.log(response.data);
            if (req.session["admin"]) {
                res.send("Edit successfully, <a href='/administrator'>Click to go to home</a>");
                //res.redirect("/mission/admin/" + req.params['missionId']);
            } else {
                res.send("Edit successfully, <a href='/coordinator'>Click to go to home</a>");
                //res.redirect("/mission/coordinator/" + req.params['missionId']);
            }
        }
        ).catch(error => {
            console.log(error);
        })
    }

})

app.post("/postMission", function (req, res) {
    if (!req.session["coordinator"]) {
        res.redirect("/coordinator");
    } else {
        console.log(typeof (req.body));
        //var jsonContent = JSON.stringify(req.body);
        var mission;
        var jobNames = req.body.jobName;
        var jobDescription = req.body.jobDescription;
        var jobs = {};
        var countries = [];
        if (Array.isArray(jobNames)) {
            for (i = 0; i < jobNames.length; i++) {
                jobs[jobNames[i]] = jobDescription[i];
            }
        } else {
            jobs[jobNames] = jobDescription;
        }
        var title = req.body.title;
        var numberOfTitle = req.body.numberOfTitle;
        var titles = {};
        if (Array.isArray(title)) {
            for (i = 0; i < title.length; i++) {
                titles[title[i]] = parseInt(numberOfTitle[i]);
            }
        } else {
            titles[title] = parseInt(numberOfTitle);
        }
        var coordinatorInfo = {};
        coordinatorInfo["name"] = req.body["firstName"] + req.body["lastName"];
        coordinatorInfo["email"] = req.body["contactEmail"];
        coordinatorInfo["phone"] = req.body["phoneNumber"];
        if (!req.body.hasOwnProperty("countriesAllowed")) {
            countries = [null];

        } else if (Array.isArray(req.body["countriesAllowed"])) {
            for (country of req.body["countriesAllowed"]) {
                countries.push(country);
            }
        }

        ;
        mission = {
            id: -1,
            missionName: req.body["missionName"],
            missionDesc: req.body["missionDescription"],
            origin: req.body["countryOfOrigin"],
            allowedCountries: countries,
            coordinatorInfo: coordinatorInfo,
            jobs: jobs,
            launchDate: req.body["launchDate"] + 'T00:00:00.000+0000',
            duration: parseInt(req.body["missionDuration"]),
            location: req.body["location"],
            cargoFor: req.body["cargoFor"],
            empReq: titles,
            status: req.body["missionStatus"],
            candidates: [],
            shuttleId: null
        };
        console.log("post", mission);
        axios.post("http://localhost:8080/api/mission", mission).then(function (response) {
            console.log("post res: " + response);
            res.redirect("/coordinatorHome");
        })
            .catch(function (error) {
                console.log(error);
            });
    }

})

app.get("/mission/coordinator/:missionId", function (req, res) {
    //console.log(req.params["missionName"]);
    if (!req.session["coordinator"]) {
        res.redirect("/coordinator");
    } else {
        var mission;
        axios.get("http://localhost:8080/api/mission/" + req.params['missionId']).then(function (response) {
            console.log(response.data);
            res.render("missionCoordinator", { "layout": null, "mission": response.data });
        }
        ).catch(error => {
            console.log(error);
        })
    }
}
)


app.get("/register", function (req, res) {
    res.render("register", { "layout": null });

})



app.listen(8000, function () {
    console.log("Listening on port 8000");
})


    // fs.appendFile("missionData.txt",jsonContent, 'utf8', function (err) {
    //     if (err) {
    //         console.log("An error occurred while writing JSON Object to File.");
    //         return console.log(err);
    //         res.send("Sorry, the mission cannot be created, <a href='/coordinatorHome'>Click to go to the homepage</a>");
    //     } else {
    //         res.send("Created Successfully, <a href='/coordinatorHome'>Click to go to the homepage</a>");
    //     }
    //     ;    
    // }    
    // )

    // for (item of data)
    // {           
    //     if(item[0]=="{"){
    //     var mission=JSON.parse(item);
    //     console.log(mission);
    //     missions.push(mission);
    //     }
    // };
    ;

        // readInterface.on('line', function(line) {
        //     //console.log(line);
        //     //console.log(typeof(line));
        //     missions.push(JSON.parse(line));
        //     //console.log(missions);

        // });

        //fs.close(fd file descriptor);
        //console.log(readInterface);
        //readInterface.close();
                // var readInterface = readline.createInterface({
        //     input: fs.createReadStream('./missionData.txt'),            
        //     output: process.stdout,
        //     console: false
        // });
        //var data = fs.readFileSync("./missionData.txt", "utf-8").split("\n");

                //var data = fs.readFileSync("./missionData.txt", "utf-8").split("\n");
        //console.log(data);