const express = require('express');
const path = require("path");
const handlebars = require("express-handlebars");
const bodyParser = require("body-parser");
const fs = require("fs");
const readline = require("readline");
//const multer = require('multer');
var session = require("express-session");
const app= express();
const axios = require("axios");

app.use("/public",express.static(path.join(__dirname,"public")));
app.use(bodyParser.urlencoded({extended: true}));
app.engine("handlebars",handlebars());
app.set("view engine", 'handlebars');
app.set('views', path.join(__dirname,'views'));
app.use(bodyParser.json())
app.use(session({
    secret:"secret code",
    resave: true,
    saveUninitialized: true
}))
// let axiosDefaults = require('axios/lib/defaults');
// axiosDefaults.baseURL = 'http://locahost:8080/';
var hbs = handlebars.create({});
//express.urlencoded([extended]);
var limitLength = function(content, maxLength){
    if(content.length > maxLength){
        content = content.substring(0,maxLength) + "...";

    }
    return content;
}

var cut = function(content,length){
    return content.substring(0,length);
}
hbs.handlebars.registerHelper("cut",cut);

app.get("/", function (req, res){
    res.sendFile(path.join(__dirname,'index.html'))
})

app.get("/administrator", function (req, res){
    if(!req.session["admin"]){
    res.render("administrator",{layout:false})
    } else {
        res.redirect("adminHome");
    }
})

app.get("/candidate", function (req, res){
 
    res.render("candidate",{layout:false});
})

app.get("/coordinator", function (req, res){
    if(!req.session["coordinator"]){
    res.render("coordinator",{layout:false});
    } else {
        res.redirect("coordinatorHome");
    }
})

app.post("/adminLogin", function(req,res){
    //console.log(req.body);
    var username=req.body['username'];
    var password=req.body['password'];
    if (username=="admin" && password=="admin")
    {
        req.session["admin"]=username;
        res.redirect("/adminHome");
    }
    else{
        
        res.send("Incorrect username or password, <a href='/administrator'>Click to go back</a> ");
        res.end();
    }
    //console.log(req.session);
    // console.log(req.session["admin"]);
})

app.post("/coordinatorLogin", function(req,res){
    //console.log(req.body);
    var username=req.body['username'];
    var password=req.body['password'];
    if (username=="coordinator" && password=="coordinator")
    {
        req.session["coordinator"]=username;
        res.redirect("/coordinatorHome");
    }
    else{
        
        res.send("Incorrect username or password, <a href='/coordinator'>Click to go back</a> ");
        res.end();
    }
    //  console.log(req.session);
    //  console.log(req.session["id"]);
    //  console.log(req.session["coordinator"]);
})




app.get("/adminHome", function(req,res){
    if(!req.session["admin"]){
        res.redirect("/administrator");
    } else {
        var missions=[];
        axios.get("http://localhost:8080/api/mission").then(function(response){
            //alert("Data: " + response + "\nStatus: ");
            console.log(response.data);
            //response = JSON.parse(response);
            for (item of response.data)
            {           
                
                //var mission=JSON.parse(item);
                console.log(item);
                missions.push(item);
                
            };
            res.render("adminHome",{layout:"adminBar", 'missions':missions});
            console.log("missions:"+missions);
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
        axios.get("http://localhost:8080/api/mission/"+req.params['missionId']).then(function(response){
        console.log(response.data);
        res.render("missionAdmin",{"layout":null,"mission":response.data});
    }
        ).catch(error => {
            console.log(error);
          })
    }
}
)

app.get("/mission/:id/shuttle/",function(req,res){
    if (!req.session["admin"]) {
        res.redirect("/administrator");
    } else {
        var missionId = req.params['id'];
        //missionId["id"] = 
        var shuttles = [];
        console.log(missionId);
        axios.get("http://localhost:8080/api/shuttle").then(function(response){
            for (item of response.data)
            {                          
                item['missionId'] = req.params['id'];
                console.log(item);
                shuttles.push(item);
                
            };
            res.render("shuttleList",{layout:"adminBar", 'shuttles':shuttles, "missionId":missionId});
            console.log("shuttles:"+shuttles);
          }).catch(error => {
            console.log(error)
          })
    }
})

app.get("/mission/:missionId/shuttle/:shuttleId",function(req,res){
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
        axios.put("http://localhost:8080/api/mission/selectShuttle/"+req.params["missionId"],req.params["shuttleId"],config).then(function (response) {
            console.log("select res: "+response);
            res.redirect("/adminHome");
          })
          .catch(function (error) {
            console.log(error);
          });
    }

})

app.get("/coordinatorHome", function(req,res){
    if(!req.session["coordinator"]){
        res.redirect("/coordinator");
    } else {
        var missions=[];
        axios.get("http://localhost:8080/api/mission").then(function(response){
            //alert("Data: " + response + "\nStatus: ");
            console.log(response.data);
            //response = JSON.parse(response);
            for (item of response.data)
            {           
                
                //var mission=JSON.parse(item);
                console.log(item);
                missions.push(item);
                
            };
            res.render("coordinatorHome",{layout:"coordinatorBar", 'missions':missions});
            console.log("missions:"+missions);
          }).catch(error => {
            console.log(error)
          })
        
      }; 
    // console.log(req.session["admin"]);
    // console.log(req.session["coordinator"]);
    // console.log(req.session["id"]);
})

app.get("/adminLogout",function(req,res){
    if(req.session["admin"]){
        delete req.session["admin"]; //not thorough
        res.redirect("/administrator");
    } else {
        res.send("You have already logged out, <a href='/administrator'>Click to go to log in</a>")
    }
})

app.get("/coordinatorLogout",function(req,res){
    if(req.session["coordinator"]){
        delete req.session["coordinator"]; //not thorough
        res.redirect("/coordinator");
    } else {
        res.send("You have already logged out, <a href='/coordinator'>Click to go to log in</a>")
    }
})

app.get("/createMission",function(req,res){
    if(!req.session["coordinator"]){
        res.redirect("/coordinator");
    } else {
        res.render("createMission",{"layout":"createMissionBar"});
    }
})

app.post("/postMission", function(req,res){
    console.log(typeof(req.body));
    //var jsonContent = JSON.stringify(req.body);
    var mission;
    var jobNames = req.body.jobName;
    var jobDescription = req.body.jobDescription;
    var jobs={};
    if(Array.isArray(jobNames)){
        for (i = 0 ; i < jobNames.length; i++){
            jobs[jobNames[i]] = jobDescription[i];
        }
    }else{
        jobs[jobNames]=jobDescription;
    }
    var title = req.body.title;
    var numberOfTitle = req.body.numberOfTitle;
    var titles = {};
    if(Array.isArray(title)){
        for (i=0;i<title.length;i++){
            titles[title[i]]= parseInt(numberOfTitle[i]);
        }
    }else{
        titles[title]= parseInt(numberOfTitle);
    }
    var coordinatorInfo = {};
    coordinatorInfo["name"]=req.body["firstName"]+req.body["lastName"];
    coordinatorInfo["email"]=req.body["contactEmail"];
    coordinatorInfo["phone"]= req.body["phoneNumber"];
    if(!req.body.hasOwnProperty("countriesAllowed")){
        req.body["countriesAllowed"]= [null];

    };
    mission = {
        id: -1,
        missionName: req.body["missionName"],
        missionDesc: req.body["missionDescription"],
        origin: req.body["countryOfOrigin"],
        allowedCountries: req.body["countriesAllowed"],
        coordinatorInfo: coordinatorInfo,
        jobs: jobs,
        launchDate: req.body["launchDate"]+'T00:00:00.000+0000',
        duration: parseInt(req.body["missionDuration"]),
        location: req.body["location"],
        cargoFor: req.body["cargoFor"],
        empReq: titles,
        status: req.body["missionStatus"],
        candidates: [],
        shuttleId: null
      };
      console.log("post",mission);
    axios.post("http://localhost:8080/api/mission",mission).then(function (response) {
        console.log("post res: "+response);
        res.redirect("/coordinatorHome");
      })
      .catch(function (error) {
        console.log(error);
      });

})

app.get("/mission/:missionId", function (req, res) {
    //console.log(req.params["missionName"]);
    if (!req.session["coordinator"]) {
        res.redirect("/coordinator");
    } else {
        var mission;
        axios.get("http://localhost:8080/api/mission/"+req.params['missionId']).then(function(response){
        console.log(response.data);
        res.render("missionCoordinator",{"layout":null,"mission":response.data});
    }
        ).catch(error => {
            console.log(error);
          })
    }
}
)




app.listen(8000, function(){
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