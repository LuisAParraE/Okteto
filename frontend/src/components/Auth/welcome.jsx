import React from "react";
import WelcomeImg from "../../welcome.svg";
import { useNavigate } from 'react-router-dom';
import Button from '@mui/material/Button';
import Typography from '@mui/material/Typography'
import "../Auth/style.css"

import CardContent from '@mui/material/CardContent';
import CardMedia from '@mui/material/CardMedia';
import Card from '@mui/material/Card';
import { padding } from "@mui/system";
import Grid from '@mui/material/Grid';
import Tooltip from '@mui/material/Tooltip';

import Paper from '@mui/material/Paper';
import { styled } from '@mui/material/styles';
import Box from '@mui/material/Box';

function Welcome() {

    let navigate = useNavigate();
    function handleClickLogin() {
        navigate('/login');
    };
    function handleClickRegister() {
        navigate('/register');
    };
  
    return (
        <Box sx={{ flexGrow: 1 }}>
            <Grid container spacing={2} className="Papa">
                <Grid container className="row1"></Grid>
                <Grid container className="row2">
                    <Grid container sm ={2}  className="row2-colum1"></Grid>
                    <Grid container sm={8} className="row2-colum2">
                        <Card className = "card">
                            <Grid container className="papa-imagen-and-welcome">
                                <Grid item  sm ={1}></Grid>
                                <Grid item  sm ={5}>
                                    <CardMedia 
                                        component="img"
                                        alt="imagen de Welcome"
                                        image={WelcomeImg}
                                    />
                                </Grid>
                                <Grid container sm ={5} className="card-buttons"  >
                                    {/* <CardContent> */}
                                        <Grid container className="card-buttons-inside">
                                            <Grid item>
                                                <Typography className="typo-welcome">WELCOME</Typography>
                                            </Grid>
                                            {/* <div> */}
                                                <Grid item sx={{marginBottom:"25px"}}>
                                                    <Tooltip title="go to login" placement="top-end">
                                                        <Button className="button-login"
                                                            variant="contained" 
                                                            size="large" 
                                                            onClick={handleClickLogin}
                                                        >Login
                                                        </Button>
                                                    </Tooltip>
                                                </Grid>
                                            {/* </div> */}
                                            {/* <div> */}
                                                <Grid item>
                                                    <Tooltip title="go to register">
                                                        <Button className="button-sing"
                                                            variant="outlined"  
                                                            size="large" 
                                                            onClick={handleClickRegister}
                                                        >Sign Up
                                                        </Button>
                                                    </Tooltip>
                                                </Grid>
                                            {/* </div> */}
                                        </Grid>
                                    {/* </CardContent> */}
                                </Grid>
                                <Grid item  sm ={1}></Grid>
                            </Grid>
                        </Card>
                    </Grid>
                    <Grid container sm={2} className="row2-colum3"></Grid>
                </Grid>
                <Grid container spacing={2} className="row3"></Grid>
                    <Typography >
                        © StyleOverflow 2021
                    </Typography>
            </Grid>
        </Box>
        // <div className='form-container'>
        //         <div className ="welcome">
        //         <Card className = "carta">
        //             <div className="alo">ALo</div>
        //             <div className ="chao">CHAO</div>
        //         </Card>
        //         {/* <Grid container direction = "column" alignItems = "center" justifyContent = "center">
        //         <Card sx={{borderRadius: 12 ,minHeight:250 ,maxHeight:700, maxWidth: 700 , display:"flex",padding:"100px 40px 60px 40px" }}>
        //             <CardMedia
        //                 component="img"
        //                 alt="imagen de Welcome"
        //                 height="200"
        //                 image={WelcomeImg}
        //             />
        //             <CardContent>
        //                 <Typography variant="h3" color="primary" paragraph fontFamily='Hepta Slab' >WELCOME</Typography>
        //                 <div className="bt1">
        //                     <Tooltip title="going to login" placement="top-end">
        //                     <Button
        //                         variant="contained" 
        //                         size="large" 
        //                         onClick={handleClickLogin}
        //                         sx={{padding:"8px 85px 8px 85px"}}
        //                     >Login
        //                     </Button>
        //                     </Tooltip>
        //                 </div>
        //                 <Tooltip title="going to register">
        //                 <Button 
        //                     variant="outlined"  
        //                     size="large" 
        //                     onClick={handleClickRegister}
        //                     sx={{padding:"8px 90px 8px 95px"}}
        //                 >Sign Up
        //                 </Button>
        //                 </Tooltip>
        //             </CardContent>
        //         </Card>
        //         </Grid>
        //         <Typography variant="body1" color="inherit" marginTop ="20px">
        //         © StyleOverflow 2021
        //       </Typography>*/}
        //         </div> 
        //     </div>
     
    );
  }
  
  export default Welcome;