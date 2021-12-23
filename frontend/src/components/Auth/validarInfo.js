export default function validarInfo(values,tipoForm){
    let errors = {}
    
    //Register
    errors.name = "Please enter only letter characters"
    errors.surname = "Please enter only letter characters"
    errors.username = "Please enter only alphanumerics characters without blankspace "
    errors.password = " user or password invalid please try again"
    errors.birthdate = " please fill this field"



    // if(tipoForm==1){
    //     //Name
    //     // if(values.name.length > 40 ){
    //     //     errors.name = " name need to be 40 characters or less"
    //     // }else if(values.name!=""){
    //     //     if(!/^[a-zA-Z\s]*$/g.test(values.name)){
    //     //         errors.name = "Please enter only letter characters"
    //     //     }
    //     // }

    //     //LastName
    //     if(values.surname.length > 40 ){
    //         errors.surname = " surname need to be 40 characters or less"
    //     }else if(values.surname!=""){
    //         if(!/^[a-zA-Z\s]*$/g.test(values.surname)){
    //             errors.surname = "Please enter only letter characters"
    //         }
    //     }
    //     //UserName
    //     if(values.username.length > 14 ){
    //         errors.username = " username need to be 14 characters or less"
    //     }else if(values.username!=""){
    //         if(!/^[a-zA-Z0-9]*$/g.test(values.username)){
    //             errors.username = "Please enter only alphanumerics characters without blankspace "
    //         }
    //     }

    //     //Password
    //     if(values.password.length !=8 ){
    //         errors.password = " user or password invalid please try again"
    //     }

    // }
    // //Login
    // else if(tipoForm ==2){
    //     //UserName
    //     if(values.username.length > 14 ){
    //         errors.username = " username need to be 14 characters or less"
    //     }else if(values.username!=""){
    //         if(!/^[a-zA-Z0-9]*$/g.test(values.username)){
    //             errors.username = "Please enter only alphanumerics characters without blankspace "
    //         }
    //     }

    //     //Password
    //     if(values.password.length !=8){
    //         errors.password = " user or password invalid please try again"
    //     }
    // }
    // else if(tipoForm==3){
    //     //NameProject
    //     if(values.nameproject.length > 40 ){
    //         errors.nameproject = " nameproject need to be 40 characters or less"
    //     }else if(values.nameproject!=""){
    //         if(!/^[a-zA-Z\s]*$/g.test(values.nameproject)){
    //             errors.nameproject = "Please enter only letter characters"
    //         }
    //     }
    //     //Description
    //     if(values.description.length > 580 ){
    //         errors.description = " description need to be 580 characters or less"
    //     }else if(values.description!=""){
    //         if(!/^[a-zA-Z\s]*$/g.test(values.description)){
    //             errors.description = "Please enter only letter characters"
    //         }
    //     }
    //     //Fecha inicio


    // }
    return errors;
}
