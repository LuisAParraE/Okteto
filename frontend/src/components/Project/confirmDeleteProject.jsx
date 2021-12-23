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
import DeleteSweepIcon from '@mui/icons-material/DeleteSweep';
import ClearIcon from '@mui/icons-material/Clear';


function ConfirmDeleteProject(props){
    function parseDateString(value, originalValue) {
        const parsedDate = isDate(originalValue)
          ? originalValue
          : parse(originalValue, "yyyy-MM-dd", new Date());
      
        return parsedDate;
      }

    const { PostDeleteProject } = Conection();
    const handleSubmitProject = data =>{
        PostDeleteProject({projectId: props.projectId,owner:2, sessionId:""}).then(res =>{
            console.log(res.data)
            
        })
        .catch(err =>{

            console.log(err)
            console.log("No pudimos eliminar el proyecto")
        })
    }

    const onSubmit = data => console.log(data);

    const [open, setOpen] = React.useState(false);
    const handleOpen = () => setOpen(true);
    const handleClose = () => setOpen(false);


    return(
        <>
            <Button onClick={handleOpen}> <DeleteSweepIcon/></Button>
            <Modal
                open={open}
                onClose={handleClose}
                aria-labelledby="modal-modal-title"
                aria-describedby="modal-modal-description"
            >
        <div className='form-container'>
        <div className='form-content-right'>
            <Grid container direction = "column" alignItems = "center" justifyContent = "center">
            <Card sx={{display:"flex", padding:"25px" , paddingTop:"2px"}}>
            <CardContent>
                <Typography>Are you sure you want to delete this project??</Typography>
            <Button onClick={handleSubmitProject}> Confirm
            </Button>
            <Button onClick={handleClose}> Cancel
            </Button>
            </CardContent>
            </Card>
            </Grid> 
         
            <Typography variant="body1" color="inherit" marginTop ="20px">
            © StyleOverflow 2021
          </Typography>
        </div>
    </div>
    </Modal>
    </>
    )
}
export default ConfirmDeleteProject;