import React from "react";

import LoginImg from "../../login2.svg";
import useForm2 from './useForm'
import validar from './validarInfo'
import TextField from '@mui/material/TextField';
import Button from "@mui/material/Button";
import Typography from "@mui/material/Typography";
import "../Auth/styleLogin.css"

import CardContent from "@mui/material/CardContent";
import CardMedia from "@mui/material/CardMedia";
import Card from "@mui/material/Card";
import Grid from "@mui/material/Grid";
import Tooltip from "@mui/material/Tooltip";
import { useNavigate } from "react-router-dom";

import Box from '@mui/material/Box';  
import FormControl from '@mui/material/FormControl';
import { useForm } from 'react-hook-form';
import { yupResolver } from '@hookform/resolvers/yup';
import * as yup from 'yup';
import Conection from "../conection";
import Session from "../../utils/session";



function Login() {

    let navigate = useNavigate();
    function handleClickRegister() {
        navigate('/register');
    };
    
const schema = yup.object().shape({
    username: yup.string().required().max(14).matches(/^[a-zA-Z0-9]*$/g,"must contains only alphanumerics characters without blankspace"),
    password: yup.string().required().min(8).max(8),
})
const { register, handleSubmit, formState: { errors ,isSubmitting, isDirty, isValid} } = useForm({
    resolver : yupResolver(schema),
    mode: "onTouched"
});

const { SetValue} = Session();
const handleSession = data =>{
    console.log(data)
    SetValue("PM_sessionId",data);
    }
const { ConectionLogin} = Conection();
const handleSubmitLogin = data =>{
    console.log(data)
    ConectionLogin(data).then(res =>{
        console.log(res.data, res.data.session_id);
        handleSession(res.data.session_id);
        navigate('/projects');
    }).catch(err =>{
        console.log(err)
        console.log("User can't be reached")
    })
    }

// const onSubmit = data => console.log(data);
// console.log(errors);
    return (
       <>
        <div className='form-container'>
            <div className='form-content-right'>
            <form onSubmit={handleSubmit(handleSubmitLogin)}>
                <Grid container direction = "column" alignItems = "center" justifyContent = "center">
                <Card sx={{marginTop:"5%", borderRadius: 12 ,minHeight:400 ,maxWidth: 700 , display:"flex", padding:"50px 60px 30px 30px" }}>
                    <CardMedia sx={{padding: "40px 0px 0px 0px"}}
                        component="img"
                        alt="imagen de Login"
                        height="250"
                        image={LoginImg}
                    />
                <CardContent>
                <Typography variant="h3" color="primary" paragraph fontFamily='Hepta Slab'>LOGIN</Typography>
                    <div className='form-inputs'>
                        <label htmlFor='username' className = 'form-label'></label>
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
                    <div className='form-inputs'>
                        <label htmlFor='password' className = 'form-label'></label>
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
                        sx={{ padding: "8px 95px 8px 95px" }}
                        type="submit"
                      >
                        Login
                      </Button>
                    </div>
                    <span className = 'form-input-login'>
                        Don't  have an account ? 
                    </span>
                    <Tooltip title="go to sign up">
                        <Button 
                            variant="outlined"  
                            size="small" 
                            onClick={handleClickRegister}
                            sx={{padding:"4px 10px 4px 10px"}}
                        >Sign Up
                        </Button>
                        </Tooltip>
                </CardContent>
                </Card>
                </Grid> 
                </form>
                <Typography variant="body1" color="inherit" marginTop ="20px">
                © StyleOverflow 2021
              </Typography>
            </div>
        </div>

    </>
  );
}

export default Login;
