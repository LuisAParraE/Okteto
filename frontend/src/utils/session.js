function Session(){

    function GetValue(key) {
        try {
            // // Allow value to be a function so we have same API as useState
            // const valueToStore =
            // value instanceof Function ? value(storedValue) : value;
            // // Save state
            // setStoredValue(valueToStore);
            // Save to local storage
            const value = window.localStorage.getItem(key); 
            console.log(value, 'localstorage result');
            return value;
        } catch (error) {
            // A more advanced implementation would handle the error case
            console.log(error);
        }
        
    }

    // Return a wrapped version of useState's setter function that ...
    // ... persists the new value to localStorage.
    const SetValue = (key,value) => {
        try {
            // // Allow value to be a function so we have same API as useState
            // const valueToStore =
            // value instanceof Function ? value(storedValue) : value;
            // // Save state
            // setStoredValue(valueToStore);
            // Save to local storage
            window.localStorage.setItem(key,value);
        } catch (error) {
            // A more advanced implementation would handle the error case
            console.log(error);
        }
    };
    const RemoveValue =(key) =>{
        try {
            // // Allow value to be a function so we have same API as useState
            // const valueToStore =
            // value instanceof Function ? value(storedValue) : value;
            // // Save state
            // setStoredValue(valueToStore);
            // Save to local storage
            window.localStorage.removeItem(key);
        } catch (error) {
            // A more advanced implementation would handle the error case
            console.log(error);
        }
    }
    function IsAuth(){
        const Id = GetValue("PM_sessionId");
        console.log(Id);
        if(Id){
            return true;
        }
        return false
    }
 
    return {
        GetValue,
        SetValue,
        RemoveValue,
        IsAuth
    };
}
export default Session;
