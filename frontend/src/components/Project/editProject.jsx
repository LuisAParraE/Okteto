import React from "react";

import Button from '@mui/material/Button';
import Typography from '@mui/material/Typography'
import TextField from '@mui/material/TextField';

import CardContent from '@mui/material/CardContent';
import Card from '@mui/material/Card';
import Grid from '@mui/material/Grid';

import { useNavigate } from 'react-router-dom';
import { useForm, SubmitHandler, Controller} from 'react-hook-form';
import { yupResolver } from '@hookform/resolvers/yup';
import * as yup from 'yup';
import { parse, isDate } from "date-fns";
import moment from "moment"
import Conection from "../conection";
import Modal from '@mui/material/Modal';
import ModeEditOutlineIcon from '@mui/icons-material/ModeEditOutline';
import ClearIcon from '@mui/icons-material/Clear';


function EditProject(props){
    function parseDateString(value, originalValue) {
        const parsedDate = isDate(originalValue)
          ? originalValue
          : parse(originalValue, "yyyy-MM-dd", new Date());
      
        return parsedDate;
      }


    const formSubmitHandler : SubmitHandler = (data) =>{
        console.log("form data is", data);
    }
    const today = moment().format('YYYY-MM-DD');

    const schema = yup.object().shape({
        name: yup.string().required().min(3).max(128).matches(/^[a-zA-Z\s]*$/g,"must contains only letters"),
        description: yup.string().required().max(580).matches(/^[a-zA-Z\s]*$/g,"must contains only letters"),
    })
    const { register, handleSubmit, formState: { errors ,isSubmitting, isDirty, isValid} } = useForm({
        resolver : yupResolver(schema),
        mode: "onTouched",
        defaultValues: {...props.project}
    });

    const { PostUpdateProject} = Conection();
    const handleSubmitProject = data =>{
        console.log(data)
        PostUpdateProject({...data,owner:2, sessionId:""}).then(res =>{
            console.log(res.data)
            
        })
        .catch(err =>{

            console.log(err)
            console.log("No pudimos modificar el proyecto")
        })
        }
    const onSubmit = data => console.log(data);
    console.log(errors);

    const [open, setOpen] = React.useState(false);
    const handleOpen = () => setOpen(true);
    const handleClose = () => setOpen(false);
    console.log(props);
    return(
        <>
            <Button onClick={handleOpen}> <ModeEditOutlineIcon/></Button>
            <Modal
                open={open}
                onClose={handleClose}
                aria-labelledby="modal-modal-title"
                aria-describedby="modal-modal-description"
            >
        <div className='form-container'>
        <div className='form-content-right'>
            <form onSubmit={handleSubmit(handleSubmitProject)}>
            <Grid container direction = "column" alignItems = "center" justifyContent = "center">
            <Card sx={{display:"flex", padding:"25px" , paddingTop:"2px"}}>
            <CardContent>

                <div style={{display:"flex", justifyContent:"end"}}>
                    <Button onClick ={handleClose}><ClearIcon/></Button>
                </div>
            <Typography variant="h5" color="primary" paragraph fontFamily='Hepta Slab'>Edit Project</Typography>
                <div className='form-inputs'>
                    <label htmlFor='name' className = 'form-label'></label>
                    <TextField
                        id="name" 
                        label="name"
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
                <div className='form-inputs'>
                    <label htmlFor='description' className = 'form-label'></label>
                    <TextField
                        id="description" 
                        label="description"
                        type="text"
                        variant="outlined"
                        placeholder="description"
                        multiline
                        rows={4}
                        InputLabelProps={{
                            shrink: true,}}
                            {...register("description")}
                        error ={!!errors.description}
                        // value={props.project.description}
                        helperText = {errors.description ? errors.description?.message : ""}
                    />
                    
                </div>
                <div className="bt1">
                  <Button disabled={!isDirty || !isValid}
                    variant="contained"
                    size="large"
                    sx={{ padding: "8px 95px 8px 95px" }}
                    type="submit"
                  >
                    Edit Project
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
    </Modal>
    </>
    )
}
export default EditProject;