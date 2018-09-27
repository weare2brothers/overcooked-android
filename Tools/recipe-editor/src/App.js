import React, { Component } from 'react';
import './App.css';
import Button from '@material-ui/core/Button';
import TextField from '@material-ui/core/TextField';
import ExpansionPanel from '@material-ui/core/ExpansionPanel';
import ExpansionPanelSummary from '@material-ui/core/ExpansionPanelSummary';
import ExpansionPanelDetails from '@material-ui/core/ExpansionPanelDetails';
import ExpandMoreIcon from '@material-ui/icons/ExpandMore';
import Typography from '@material-ui/core/Typography';
import Grid from '@material-ui/core/Grid';
import data from './recipes.json';
import AddIcon from '@material-ui/icons/Add';
import MenuItem from '@material-ui/core/MenuItem';

class App extends Component {
    constructor(props) {
        super(props);
        this.state = {
            data
        }
    }

    handleChange = (key, field) => e => {
        const data = Object.assign({}, this.state.data);
        data.recipes[key][field] = e.target.value
        this.setState({ data });
    }

    render() {
        const { recipes } = this.state.data
        return (
            <div>
                {Object.keys(recipes).map(key => {
                    const recipe = recipes[key];
                    return (
                        <ExpansionPanel key={key}>
                            <ExpansionPanelSummary expandIcon={<ExpandMoreIcon />}>
                                <Typography>{recipe.title}</Typography>
                            </ExpansionPanelSummary>
                            <ExpansionPanelDetails>
                                <Grid container spacing={24}>
                                    <Grid item xs={12}>
                                        <TextField label="Title" fullWidth margin="normal" value={recipe.title} onChange={this.handleChange(key, 'title')} />
                                    </Grid>
                                    <Grid item xs={12}>
                                        <TextField label="Serves" margin="normal" value={recipe.serves} onChange={this.handleChange(key, 'serves')} type="number" />
                                        <TextField label="Makes" margin="normal" value={recipe.makes} onChange={this.handleChange(key, 'makes')} type="number" />
                                        <TextField label="Prep Time" margin="normal" value={recipe.prepTime} onChange={this.handleChange(key, 'prepTime')} type="number" />
                                        <TextField label="Cook Time" margin="normal" value={recipe.cookTime} onChange={this.handleChange(key, 'cookTime')} type="number" />
                                    </Grid>
                                    <Grid item xs={12}>
                                        <Typography>Ingredients</Typography>
                                        {recipe.ingredients.map((ing, i) => {
                                            return (
                                                <div key={`recipe-${key}ing-${i}`}>
                                                    <TextField label="Value" margin="normal" type="number" />
                                                    <TextField
                                                        select
                                                        label="Ingredient Type"
                                                        margin="normal"
                                                        className="defaultFormFieldWidth"
                                                        value="optionvalue1"
                                                    >
                                                        <MenuItem key={"test1"} value={"optionvalue1"}>option label 1</MenuItem >
                                                        <MenuItem key={"test2"} value={"optionvalue2"}>option label 2</MenuItem >
                                                        <MenuItem key={"test3"} value={"optionvalue3"}>option label 3</MenuItem >
                                                    </TextField>
                                                    <TextField label="Recipe Display" margin="normal" />
                                                </div>
                                                
                                            )
                                        })}
                                    </Grid>
                                    <Grid item xs={12}>
                                        <Typography>Method</Typography>
                                    </Grid>
                                </Grid>
                            </ExpansionPanelDetails>
                        </ExpansionPanel>
                    )
                })}
                <br />
                <Button variant="contained" color="primary"><AddIcon /></Button>
                <br /><br /><br />
                <TextField label="Output" margin="normal" value={JSON.stringify(this.state.data)} variant="outlined" />
            </div>
        );
    }
}

export default App;
