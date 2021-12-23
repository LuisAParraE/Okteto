import React from "react";
import useForm2 from "./Auth/useForm";
import validar from "./Auth/validarInfo"
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
import Conection from "./conection";
import Modal from '@mui/material/Modal';
import AddCircleIcon from '@mui/icons-material/AddCircle';
import ClearIcon from '@mui/icons-material/Clear';
import Session from "../utils/session";


function NewProjects(){
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
        beginDate: yup.date().transform(parseDateString).min(today).required(),
        endDate: yup.date().transform(parseDateString).min(today).required()
    })
    const { register, handleSubmit, formState: { errors ,isSubmitting, isDirty, isValid} } = useForm({
        resolver : yupResolver(schema),
        mode: "onTouched"
    });

    const { PostCreateProject} = Conection();
    const{ GetValue } = Session();
    const handleSubmitProject = data =>{
        const sessionId= GetValue("PM_sessionId")
        console.log(data, sessionId, '<-----')
        PostCreateProject({...data,owner:2, sessionId}).then(res =>{
            console.log(res.data)   
        })
        .catch(err =>{

            console.log(err)
            console.log("No pudimos crear el proyecto")
        })

        }
    const onSubmit = data => console.log(data);
    console.log(errors);

    const [open, setOpen] = React.useState(false);
    const handleOpen = () => setOpen(true);
    const handleClose = () => setOpen(false);

    return(
        <>
            <Button onClick={handleOpen}> <AddCircleIcon/></Button>
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
            <Typography variant="h5" color="primary" paragraph fontFamily='Hepta Slab'>New Project</Typography>
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
                        helperText = {errors.description ? errors.description?.message : ""}
                    />
                    
                </div>
                <div className='form-inputs'>
                    <label htmlFor='beginDate' className = 'form-label'></label>
                    <TextField 
                        id="beginDate"
                        label = "beginDate"
                        type = "date"
                        variant="outlined" 
                        InputLabelProps={{
                            shrink: true,
                        }}
                        {...register("beginDate")}
                        error ={!!errors.beginDate}
                        helperText = {errors.beginDate ? errors.beginDate?.message : ""}   
                    />
                    
                </div>
                <div className='form-inputs'>
                    <label htmlFor='endDate' className = 'form-label'></label>
                    <TextField 
                        id="endDate"
                        label = "endDate"
                        type = "date"
                        variant="outlined" 
                        InputLabelProps={{
                            shrink: true,
                        }}
                        {...register("endDate")}
                        error ={!!errors.endDate}
                        helperText = {errors.endDate ? errors.endDate?.message : ""}   
                    />
                </div>
                <div className="bt1">
                  <Button disabled={!isDirty || !isValid}
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
    </Modal>
    </>
    )
}
export default NewProjects;