import React from "react";
import useForm from "./Auth/useForm";
import validar from "./Auth/validarInfo"
import Button from '@mui/material/Button';
import Typography from '@mui/material/Typography'
import TextField from '@mui/material/TextField';

import CardContent from '@mui/material/CardContent';
import Card from '@mui/material/Card';
import Grid from '@mui/material/Grid';

import { useNavigate } from 'react-router-dom';

function Projects(){
    const {
        handleChangeProjects,
        valuesProjects,
        handleSubmitProjects,
        errors,
        } = useForm(validar,3);

    return(
        <div className='form-container'>
        <div className='form-content-right'>
            <form className='form' onSubmit={handleSubmitProjects}>
            <Grid container direction = "column" alignItems = "center" justifyContent = "center">
            <Card sx={{borderRadius: 12 ,minHeight:400 ,maxWidth: 700 , display:"flex", padding:"50px 60px 30px 30px" }}>
            <CardContent>
            <Typography variant="h3" color="primary" paragraph fontFamily='Hepta Slab'>New Project</Typography>
                <div className='form-inputs'>
                    <label htmlFor='name' className = 'form-label'></label>
                    <TextField 
                        required
                        id="name" 
                        label="name" 
                        variant="outlined" 
                        name = 'name'
                        placeholder = 'name'
                        value = {valuesProjects.name}
                        onChange = {handleChangeProjects}
                        error = {errors.name }
                        helperText = {<p>{errors.name}</p>}

                    />
                    {/* {errors.nameproject && <p>{errors.nameproject}</p>} */}

                </div>
                <div className='form-inputs'>
                    <label htmlFor='description' className = 'form-label'></label>
                    <TextField 
                        required
                        id="description" 
                        label="description" 
                        variant="outlined" 
                        name = 'description'
                        type = 'description'
                        placeholder = 'description'
                        multiline
                        rows = {4}
                        value = {valuesProjects.description}
                        onChange = {handleChangeProjects}
                        error = {errors.description}
                        helperText = {<p>{errors.description}</p>}
                    />
                    {/* {errors.description && <p>{errors.description}</p>} */}
                    
                </div>
                <div className='form-inputs'>
                    <label htmlFor='beginDate' className = 'form-label'></label>
                    <TextField
                        required
                        id="beginDate" 
                        variant="outlined" 
                        name = 'beginDate'
                        type = "date"
                        value = {valuesProjects.beginDate}
                        onChange = {handleChangeProjects}
                    />
                    {/* {errors.start && <p>{errors.start}</p>} */}
                    
                </div>
                <div className='form-inputs'>
                    <label htmlFor='endDate' className = 'form-label'></label>
                    <TextField 
                        required
                        id="endDate" 
                        variant="outlined" 
                        name = 'endDate'
                        type = 'date'
                        value = {valuesProjects.endDate}
                        onChange = {handleChangeProjects}
                    />
                    {/* {errors.finish && <p>{errors.finish}</p>}  */}
                </div>
                <div className="bt1">
                  <Button
                    variant="contained"
                    size="large"
                    sx={{ padding: "8px 95px 8px 95px" }}
                    type="submit"
                  >
                    New Project
                  </Button>
                </div>
            </CardContent>
            </Card>
            </Grid> 
            </form>
            <Typography variant="body1" color="inherit" marginTop ="20px">
            © StyleOverflow 2021
          </Typography>
        </div>
    </div>

    )
}
export default Projects;