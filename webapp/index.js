const express = require('express');
const axios = require('axios');
var cookieParser = require('cookie-parser');
const bodyParser = require('body-parser');

const userCompanyServiceBase = "http://localhost:8080";
const authenticateUrl = `${userCompanyServiceBase}/login`;
const userUrl = `${userCompanyServiceBase}/user`;
const companyUrl = `${userCompanyServiceBase}/company`;
const pingUrl = `${userCompanyServiceBase}/ping`;


const app = express();

app.use(express.static("public"));
app.use(cookieParser());
app.use(bodyParser());


app.post('/ping', (req, res) => {
    const { authToken } = req.cookies;
    const config = {
    	headers: {
            authToken
    	}
    };
    axios.post(pingUrl,{}, config)
         .then((response) => {
         	res.status(200);
         	res.send({});
         })
         .catch((error) => {
         	res.status(401);
         	res.send({});
         });
});

app.get('/userInfo', (req, res) => {

    const { authToken } = req.cookies;
    const config = {
    	headers: {
            authToken
    	}
    };
    axios.get(userUrl, config)
         .then((response) => {
             res.status(200);
             res.render("home.ejs", response.data);
         })
         .catch((error) => {
             res.status(401);
             res.render("login.ejs");
         });
});


app.post('/authenticate', (req, res) => {
    axios.post(authenticateUrl, req.body)
         .then((response) => {
             res.status(200);
             res.send(response.data);
         })
         .catch((error) => {
             res.status(401);
             res.send({});
         });
});

app.get('/', (req, res) => {
	res.render("login.ejs");
});


app.get('/login', (req, res) => {
    res.render("login.ejs");
});

app.get('/company', (req, res) => {
	const { authToken } = req.cookies;
    const config = {
    	headers: {
            authToken
    	}
    };
    axios.get(companyUrl, config)
         .then((response) => {
         	res.status(200);
         	res.render("company.ejs", response.data);
         })
         .catch((error) => {
         	res.status(401);
         	res.render("login.ejs");
         });
});


const server = app.listen(8000, () => {
  console.log(`Express running → PORT ${server.address().port}`);
});