Vanguard
===
### *The personal intelligent health adviser*
Team Members: Xi Pu, Siddhant Khanna, and Benjamin Xu
## Why did we make Vanguard?
There is a stunning lack of health awareness that is prevalent even in developed countries like the United States. Many individuals which are outwardly healthy are actually already at high risk for future disasters like cardiovascular events. This "faux-health" is a reflection of the lack of widespread health education regarding important modifiable bio-markers and their impact on human health and longevity.
## What does Vanguard do?
Vanguard uses a scientifically verified high-accuracy algorithm based on significant human bio-marker data to generate an estimate of the user's level of health. Vanguard does this through user inputted health data; many users will not have access to the full battery of bio-markers, so missing values will be resolved through data imputation powered by machine-learning methods in order to provide accurate user profiles despite incomplete data.
## How did we do it?
### Front End
#### Material Design
### Back End
#### Risk Model
##### Framingham Risk Score
The Framingham Risk Score is a powerful and surprisingly accurate tool for the assessment of 10-year cardiovascular risk, and was developed on the basis of data from one of the largest and longest-running cohorts in clinical research. We used this model as reference, and as inspiration as we borrowed upon the methods that the scientists used to create this model, and adapted them to our context.
##### CSPPT Risk Score
The CSPPT is one of the most famous recently conducted clinical trials, for both is ingenuity of design and the powerful implications of its findings. We were fortunate enough to have access to a dataset of over 20,000 individuals from this trial, including important information for a substantial amount of significant bio-markers.
#### Data Imputation
##### Machine Learning
##### Adapting to Java
## What challenges did we encounter?
TBD
## Anything we're proud of?
TBD
## What did we learn?
TBD
## What's next for Vanguard?
TBD
## Limitations
### User Characteristics
Due to the nature of the data we had to work with, our algorithms work best with individuals who are more similar to the individuals present in our data; in practical terms, this means that adults and elderly would be more likely to receive more accurate results compared to younger users, for example. 
## References
* Liu, Y., & Gopalakrishnan, V. (2017). An overview and evaluation of recent machine learning imputation methods using cardiac imaging data. Data, 2(1), 8.
*  "NHLBI, Estimate of 10-Year Risk for CHD". Nhlbi.nih.gov. Retrieved 2018-10-12.
* J. Vesanto, J.E. Himberg, E. Alhoniemi, J. Parhankangas
Report A57
SOM Toolbox for Matlab 5, Helsinki University of Technology, Finland (2000)
* L. Folguera, J. Zupan, D. Cicerone, J.F. Magallanes
Self-organizing maps for imputation of missing data in incomplete data matrices
Chemom. Intell. Lab. Syst., 143 (2015), pp. 146-151
