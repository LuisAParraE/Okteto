import { useState } from "react";
import Conection from "../conection";

const useForm = (validate) => {
  const { ConectionRegister, ConectionLogin, PostCreateProject } = Conection();

  const [valuesRegister, setValuesRegister] = useState({
    name: "",
    surname: "",
    birthdate: "",
    username: "",
    password: "",
    app_user_role: true,
    email: "hello@hello.com",
  });
  const [valuesLogin, setValuesLogin] = useState({
    username: "",
    password: "",
  });

  const [valuesProjects, SetValuesProjects] = useState({
    name: "",
    description: "",
    beginDate: "",
    endDate: "",
    owner:1,
    sessionId:"169a6789-77da-466a-8de1-9ee4a24c9905"

  });

  const [errors, setErrors] = useState(false);
  // //VAlidando Nombre
  //     const[name,setName] = useState("")
  //     const[validName,setValidName] = useState(false)

  //     useEffect(()=>{
  //         if(validName(name)){
  //             setValidName(true)
  //         }else{
  //             setValidName(false)
  //         }
  //     },[name])

  const handleChangeRegister = (e) => {
    const { name, value } = e.target;
    setValuesRegister({
      ...valuesRegister,
      [name]: value,
    });
  };
  const handleChangeLogin = (e) => {
    const { name, value } = e.target;
    console.log(name, value);
    setValuesLogin({
      ...valuesLogin,
      [name]: value,
    });
    console.log(
      valuesLogin,
      "values login",
      valuesLogin.username.replace(" ", "")
    );
  };
  const handleChangeProjects = (e) => {
    const { name, value } = e.target;
    SetValuesProjects({
      ...valuesProjects,
      [name]: value,
    });
  };

  const handleSubmitRegister = (e) => {
    e.preventDefault();
    const formValidate = validate(valuesRegister, 1);

    if (Object.keys(formValidate).length > 0) {
      setErrors(formValidate);
    } else ConectionRegister(valuesRegister);
  };

  const handleSubmitLogin = (e) => {
    e.preventDefault();
		const formValidate = validate(valuesLogin, 2);

		if (Object.keys(formValidate).length > 0) {
			setErrors(formValidate)
		} else ConectionLogin(valuesLogin);
  };

  const handleSubmitProjects = (e) => {
    e.preventDefault();
    const formValidate = validate(valuesProjects, 3);
    if (Object.keys(formValidate).length > 0) {
			setErrors(formValidate)
		} else PostCreateProject(valuesProjects);
  };

  return {
    handleChangeRegister,
    handleChangeLogin,
    valuesRegister,
    valuesLogin,
    handleSubmitRegister,
    handleSubmitLogin,
    handleChangeProjects,
    valuesProjects,
    handleSubmitProjects,
    errors,
  };
};

export default useForm;
