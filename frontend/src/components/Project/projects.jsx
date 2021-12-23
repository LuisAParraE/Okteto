import React from "react";
import Typography from '@mui/material/Typography'
import TextField from '@mui/material/TextField';

import CardContent from '@mui/material/CardContent';
import Card from '@mui/material/Card';
import Grid from '@mui/material/Grid';
import { Button, Paper } from "@mui/material";
import AddCircleIcon from '@mui/icons-material/AddCircle';
import NewProjects from "../newprojects";
import EditProject from "./editProject";
import ConfirmDeleteProject from "./confirmDeleteProject";

const projects =[
    {
        name:"A",
        description:"lala",
        beginDate:"10-12-2021",
        endDate:"11-12-2021",
        id:1,

    },
    {
        name:"B",
        description:"lala",
        beginDate:"10-12-2021",
        endDate:"11-12-2021",
        id:2,

    },
    {
        name:"C",
        description:"lala",
        beginDate:"10-12-2021",
        endDate:"11-12-2021",
        id:3,

    },
    {
        name:"D",
        description:"lala",
        beginDate:"10-12-2021",
        endDate:"11-12-2021",
        id:4,

    },
    {
        name:"E",
        description:"lalalelito",
        beginDate:"21-12-2021",
        endDate:"30-12-2021",
        id:5,

    }
]

const Otherprojects =[
    {
        name:"A",
        description:"lala",
        beginDate:"10-12-2021",
        endDate:"11-12-2021",
        id:1,

    },
    {
        name:"B",
        description:"lala",
        beginDate:"10-12-2021",
        endDate:"11-12-2021",
        id:2,

    },
    {
        name:"C",
        description:"lala",
        beginDate:"10-12-2021",
        endDate:"11-12-2021",
        id:3,

    },
    {
        name:"D",
        description:"lala",
        beginDate:"10-12-2021",
        endDate:"11-12-2021",
        id:4,

    },
    {
        name:"E",
        description:"lalalelito",
        beginDate:"21-12-2021",
        endDate:"30-12-2021",
        id:5,

    }
]



function Projects(){
    
    return(
        
        <div className='form-container'>
            <div className='form-content-right'>
                <Grid container direction = "column" >
                    <Typography variant="h4" color="white" paragraph fontFamily='Hepta Slab'>Projects</Typography>
                        <NewProjects/>
                    <Grid container spacing={4} justifyContent ="center">

                        {projects.map((item, index) => (
                            <Grid item>
                                <Paper key={item.id} sx={{padding:"25px"}} >
                                    <div>
                                        {item.name}
                                    </div>
                                    <div>
                                        {item.description}
                                    </div>
                                    <div>
                                        {item.beginDate}
                                    </div>
                                    <div>
                                        {item.endDate}
                                    </div>
                                    <ConfirmDeleteProject projectId={item.id}/>
                                    <EditProject project = {item}/>
                                </Paper>
                            </Grid>

                        ))}
                    </Grid>
                    <Typography variant="h4" color="white" paragraph fontFamily='Hepta Slab'>Others Projects</Typography>
                    <Grid container spacing={4} justifyContent ="center">

                        {Otherprojects.map((item, index) => (
                            <Grid item>
                                <Paper key={item.id} sx={{padding:"25px"}} >
                                    <div>
                                        {item.name}
                                    </div>
                                    <div>
                                        {item.description}
                                    </div>
                                    <div>
                                        {item.beginDate}
                                    </div>
                                    <div>
                                        {item.endDate}
                                    </div>
                                </Paper>
                            </Grid>

                        ))}
                    </Grid>
                </Grid> 
                <Typography variant="body1" color="inherit" marginTop ="20px">
                © StyleOverflow 2021
                </Typography>
            </div>
        </div>

    )
}
export default Projects;