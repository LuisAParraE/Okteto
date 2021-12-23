import * as React from 'react';
import Box from '@mui/material/Box';
import Drawer from '@mui/material/Drawer';
import AppBar from '@mui/material/AppBar';
import CssBaseline from '@mui/material/CssBaseline';
import Toolbar from '@mui/material/Toolbar';
import List from '@mui/material/List';
import Typography from '@mui/material/Typography';
import ListItem from '@mui/material/ListItem';
import ListItemIcon from '@mui/material/ListItemIcon';
import ListItemText from '@mui/material/ListItemText';
import SearchIcon from '@mui/icons-material/Search';
import { styled, alpha } from '@mui/material/styles';
import InputBase from '@mui/material/InputBase';
import DashboardRoundedIcon from '@mui/icons-material/DashboardRounded';
import DescriptionOutlinedIcon from '@mui/icons-material/DescriptionOutlined';
import DragList from "./DragList";
import ScheduleRoundedIcon from '@mui/icons-material/ScheduleRounded';
import Grid from '@mui/material/Grid';
import{ Link } from "react-router-dom"


import Paper from '@mui/material/Paper';
const drawerWidth = 240;

const Search = styled('div')(({ theme }) => ({
    position: 'relative',
    borderRadius: theme.shape.borderRadius,
    backgroundColor: alpha(theme.palette.common.white, 0.15),
    '&:hover': {
      backgroundColor: alpha(theme.palette.common.white, 0.25),
    },
    marginLeft: 0,
    width: '100%',
    [theme.breakpoints.up('sm')]: {
      marginLeft: theme.spacing(1),
      width: 'auto',
    },
  }));
  
  const SearchIconWrapper = styled('div')(({ theme }) => ({
    padding: theme.spacing(0, 2),
    height: '100%',
    position: 'absolute',
    pointerEvents: 'none',
    display: 'flex',
    alignItems: 'center',
    justifyContent: 'center',
  }));
  
  const StyledInputBase = styled(InputBase)(({ theme }) => ({
    color: 'inherit',
    '& .MuiInputBase-input': {
      padding: theme.spacing(1, 1, 1, 0),
      // vertical padding + font size from searchIcon
      paddingLeft: `calc(1em + ${theme.spacing(4)})`,
      transition: theme.transitions.create('width'),
      width: '100%',
      [theme.breakpoints.up('sm')]: {
        width: '12ch',
        '&:focus': {
          width: '20ch',
        },
      },
    },
  }));

  const SidebarData = [
    {
        title: "Dashboard",
        path: '/dashboard',
    },
  ]
export default function ClippedDrawer() {
  return (
    <React.Fragment>
      <Box sx={{ display: 'flex' }}>
        <CssBaseline />
        <AppBar position="fixed" sx={{ zIndex: (theme) => theme.zIndex.drawer + 1 }}>
          <Toolbar>
            <Typography 
            variant="h6" noWrap component="div" 
            sx={{ flexGrow: 1, display: { xs: 'none', sm: 'block' } }}>
              Welcome Back
            </Typography>
            <Search>
              <SearchIconWrapper>
                <SearchIcon />
              </SearchIconWrapper>
              <StyledInputBase
                placeholder="Search…"
                inputProps={{ 'aria-label': 'search' }}
              />
            </Search>
          </Toolbar>
        </AppBar>
        <Drawer
          variant="permanent"
          sx={{
            width: drawerWidth,
            flexShrink: 0,
            [`& .MuiDrawer-paper`]: { width: drawerWidth, boxSizing: 'border-box' },
          }}
        >
          <Toolbar />
            <Box sx={{ overflow: 'auto' }}>
              <List>
              {SidebarData.map((item, index) => (
                <Link to ={item.path}>
                  <ListItem button >
                  <ListItemIcon>
                    <DashboardRoundedIcon/>{item.title}  <ListItemText/>
                  </ListItemIcon>
                </ListItem>
                </Link>
                
              ))}
              
              </List>
            </Box>
            
          </Drawer>
          

          <Box
          sx={{
            display: 'flex',
            '& > :not(style)': {
              m: 1,
              width: 180,
              height: 180,
              marginTop: 10,
              marginLeft:10,
            },
          }}
        >
          {/* <Grid container >
            <Grid item xs={3} md={12}>
              <Paper > 1</Paper>
            </Grid>
            <Grid item xs={3} md={12}>
              <Paper> 2</Paper>
            </Grid>
            <Grid item  xs={3} md={12}>
              <Paper> 3 </Paper>
            </Grid>
            <Grid item  xs={3} md={12}>
              <Paper> 4 </Paper>
            </Grid>
          </Grid> */}
            {/* <Paper variant="outlined" square />
              <Paper variant="outlined" square />
              <Paper variant="outlined" square />
              */}
              
        </Box>
        <DragList />
        
      <Toolbar />
        
      </Box>
      
    </React.Fragment>
    
    
            
    
  );
}