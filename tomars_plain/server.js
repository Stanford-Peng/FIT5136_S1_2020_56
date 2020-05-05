const express = require('express');
const path = require("path");
const handlebars = require("express-handlebars");
const bodyParser = require("body-parser");
//const multer = require('multer');
var session = require("express-session");
const app= express();

app.use("/public",express.static(path.join(__dirname,"public")));
app.use(bodyParser.urlencoded({extended: true}));
app.engine("handlebars",handlebars());
app.set("view engine", 'handlebars');
app.set('views', path.join(__dirname,'views'));
//app.use(bodyParser.json())
app.use(session({
    secret:"secret code",
    resave: true,
    saveUninitialized: true
}))
//express.urlencoded([extended]);

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
        res.redirect("/adminLogin");
    } else {
        res.send("To do");
    }
    // console.log(req.session["admin"]);
    // console.log(req.session["coordinator"]);
    // console.log(req.session["id"]);
})

app.get("/coordinatorHome", function(req,res){
    if(!req.session["coordinator"]){
        res.redirect("/coordinatorLogin");
    } else {
        res.render("coordinatorHome",{layout:"coordinatorBar"});
    }
    // console.log(req.session["admin"]);
    // console.log(req.session["coordinator"]);
    // console.log(req.session["id"]);
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
        res.redirect("/coordinatorLogin");
    } else {
        res.render("createMission",{layout:false});
    }
})

app.post("/postMission", function(req,res){
    console.log(req.body);
})


app.listen(8000, function(){
    console.log("Listening on port 8000");
})


