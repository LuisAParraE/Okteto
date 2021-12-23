import React from "react";
import RegisterImg from "../../welcome1.svg";
import useForm2 from './useForm'
import validar from './validarInfo'
import Button from '@mui/material/Button';
import Typography from '@mui/material/Typography'
import TextField from '@mui/material/TextField';
// import "../Auth/styleRegister.css"

import CardContent from "@mui/material/CardContent";
import CardMedia from "@mui/material/CardMedia";
import Card from "@mui/material/Card";
import Grid from "@mui/material/Grid";

import Tooltip from '@mui/material/Tooltip';
import { useNavigate } from 'react-router-dom';
import Box from '@mui/material/Box';
import { useForm, SubmitHandler, Controller} from 'react-hook-form';
import { yupResolver } from '@hookform/resolvers/yup';
import * as yup from 'yup';
import { parse, isDate } from "date-fns";
import moment from "moment";
import Conection from "../conection";

function Register() {

        let navigate = useNavigate();
        function handleClickLogin() {
            navigate('/login');
        };

        function parseDateString(value, originalValue) {
            const parsedDate = isDate(originalValue)
              ? originalValue
              : parse(originalValue, "yyyy-MM-dd", new Date());
          
            return parsedDate;
          }


      
    const { ConectionRegister} = Conection();
    const handleSubmitRegister = data =>{
        console.log(data)
        ConectionRegister({...data, email:""}).then(res =>{
          console.log(res.status, res.data);
          navigate('/');
        }).catch(err =>{
            console.log(err)
            console.log("User can't be created")
        })
    }

    const today = moment().format('YYYY-MM-DD');
    const schema = yup.object().shape({
        name: yup.string().required().min(3).max(40).matches(/^[a-zA-Z\s]*$/g,"must contains only letters"),
        surname: yup.string().required().max(40).matches(/^[a-zA-Z\s]*$/g,"must contains only letters"),
        birthdate: yup.date().transform(parseDateString).max(today).required(),
        username: yup.string().required().max(14).matches(/^[a-zA-Z0-9]*$/g,"must contains only alphanumerics characters without blankspace"),
        password: yup.string().required().min(8).max(8),
    })
    const { register, handleSubmit, formState: { errors ,isSubmitting, isDirty, isValid} } = useForm({
        resolver : yupResolver(schema),
        mode: "onTouched"
    });


     return (   
        <div className="form-container">
        <div className="form-content-right">
          <div className="form">
            <Grid
              container
              direction="column"
              alignItems="center"
              justifyContent="center"
            >
              <Card
                sx={{
                  borderRadius: 12,
                  maxWidth: 700,
                  display: "flex",
                  padding: "10px 60px 10px 10px",
                }}
              >
                <CardMedia
                    className="register-image"
                  sx={{ padding: "100px 0px 0px 50px" }}
                  component="img"
                  alt="imagen de Register"
                  height="250"
                  image={RegisterImg}
                />
                <CardContent>
                <form onSubmit={handleSubmit(handleSubmitRegister)}>
                  <Typography
                    variant="h3"
                    color="primary"
                    paragraph
                    fontFamily="Hepta Slab"
                  >
                    SIGN UP
                  </Typography>
  
                  <div className="form-inputs">
                    <label htmlFor="name" className="form-label"></label>
                    <TextField
                        id="name" 
                        label="Name"
                        type="text"
                        variant="outlined"
                        placeholder="name"
                        InputLabelProps={{
                            shrink: true,}}
                            {...register("name")}
                        error ={!!errors.name}
                        helperText = {errors.name ? errors.name?.message : ""}
                    />
                  </div>
                  <div className="form-inputs">
                    <label htmlFor="surname" className="form-label"></label>
                    <TextField
                        id="surname" 
                        label="Surname"
                        type="text"
                        variant="outlined"
                        placeholder="surname"
                        InputLabelProps={{
                            shrink: true,}}
                            {...register("surname")}
                        error ={!!errors.surname}
                        helperText = {errors.surname ? errors.surname?.message : ""}
                />
                  </div>
                  <div className="form-inputs">
                    <label htmlFor="birthdate" className="form-label"></label>
                    <TextField 
                        id="birthdate"
                        label = "Birthdate"
                        type = "date"
                        variant="outlined" 
                        InputLabelProps={{
                            shrink: true,
                        }}
                        {...register("birthdate")}
                        error ={!!errors.birthdate}
                        helperText = {errors.birthdate ? errors.birthdate?.message : ""}   
                    />
                  </div>
                  <div className="form-inputs">
                    <label htmlFor="username" className="form-label"></label>
                    <TextField
                        id="username" 
                        label="UserName"
                        type="text"
                        variant="outlined"
                        placeholder="username"
                        InputLabelProps={{
                            shrink: true,}}
                            {...register("username")}
                        error ={!!errors.username}
                        helperText = {errors.username ? errors.username?.message : ""}
                    />
                  </div>
                  <div className="form-inputs">
                    <label htmlFor="password" className="form-label"></label>
                    <TextField
                        id="password" 
                        label="Password"
                        type="password"
                        variant="outlined"
                        placeholder="password"
                        InputLabelProps={{
                            shrink: true,}}
                            {...register("password")}
                        error ={!!errors.password}
                        helperText = {errors.password ? errors.password?.message : ""}
                />
                </div>
                <div className="bt1">
                    <Button disabled={!isDirty || !isValid}
                        variant="contained" 
                        size="large" 
                        sx={{padding:"8px 70px 8px 60px"}} 
                        type = 'submit' 
                        alignText= "center">Sign Up
                    </Button>
                </div>
                  <span className="form-input-login">
                    Already have an account ?
                  </span>
                  <Tooltip title="go to login" placement="top-end">
                    <Button
                      variant="outlined"
                      size="small"
                      onClick={handleClickLogin}
                    //   sx={{ padding: "2px 10px 2px 10px" }}
                    >
                      Login
                    </Button>
                  </Tooltip>
                  </form>
                </CardContent>
              </Card>
            </Grid>
          </div>
          <Typography variant="body1" color="inherit" marginTop="20px">
            © StyleOverflow 2021
          </Typography>
        </div>
      </div>
    );
 }
  
  export default Register;
  
