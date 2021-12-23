import axios from 'axios'

/*
 const api = axios.create({
     baseURL: "http://localhost:13312/api/v1"
 })
*/
const api = axios.create({
    baseURL: "https://styleoverflow-back-luisaparrae.cloud.okteto.net/api/v1"
})

function Conection(){


    const ConectionRegister = data => {
        console.log(data)
        return api.post('/registration',data);
    }

    const ConectionLogin = data => {
        console.log(data)
        return api.post('/login',data);
        
    }
    const DoLogout = data => {
        console.log(data)
        return api.post('/logout',data);
    }

    const PostCreateProject = data => {
        //project/create
        console.log(data)
        return api.post('/project/create',data);
        
    }

    const PostUpdateProject = data => {
        ///project/update
        console.log(data)
        return api.post('/project/update',data);
        

    }

    const PostDeleteProject = data => {
        ///project/delete
        console.log(data)
        return api.post('/project/delete',data);
    }


    return {
        ConectionRegister, 
        ConectionLogin,
        PostCreateProject, 
        PostDeleteProject,
        PostUpdateProject,
        DoLogout
    }
}
export default Conection