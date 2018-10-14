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
Google's Material Components is an open-source drop-in replacement for Android's Design Support Library, and provides us with an adaptable system of guidelines, components, and tools facilitating the implementation of the best practices of UI design.
### Back End
#### Risk Model
##### Framingham Risk Score
The Framingham Risk Score is a powerful and surprisingly accurate tool for the assessment of 10-year cardiovascular risk, and was developed on the basis of data from one of the largest and longest-running cohorts in clinical research. We used this model as reference, and as inspiration as we borrowed upon the methods that the scientists used to create this model, and adapted them to our context.
##### CSPPT Risk Score
The CSPPT is one of the most famous recently conducted clinical trials, for both its ingenuity of design and the powerful implications of its findings. We were fortunate enough to have access to a dataset of over 20,000 individuals from this trial, including important information for a substantial amount of significant bio-markers. Using this data, we developed our own risk model based on the Cox Proportional Hazards Model and augmented it with additional statistical transformations in order to produce a standardized scoring system.
#### Data Imputation
While science has provided clinicians with a plethora of bio-markers for common diseases and overall health, these bio-markers are often unable to be applied in a useful way due to many individuals not having access to the examinations and assays that would produce this data. As a result, classical models for estimating risk which rely on all this data being present often fall flat in practice when most of this data is missing. As a result, we decided that in order to effectively implement a risk model, a central aspect of our technology would involve a powerful and accurate method for imputing these missing values so that our risk algorithms could function at the highest degrees of specificity and sensitivity possible. Thus, we turned towards data imputation, which is a general term for methods to fill in missing or invalid data.
##### Machine Learning
While classical heuristics for imputation methods exist, they rarely provide accurate estimations as to the missing values. We therefore turned towards the more advanced and powerful, but also more difficult to implement strategy of using machine learning as an imputation tool. While many of these methods also exist in the form of different algorithms, we decided to base our design on the well-documented open-source implementation SOM-Toolkit, which provides utilities for the creation and training of Self Organizing Maps, a neural-network based structure, that can be used after training with a sufficiently sized dataset to impute missing values from a given input vector.
## What challenges did we encounter?
### The Polylingual Conundrum
#### Matlab to Java
While earlier it was mentioned that an open-source implementation of SOM-based imputation was available for Matlab, the caveat that was not mentioned was that due to our Android-centric app development, we were working in a Java-powered environment, *id est* there was a literal language barrier preventing us from using the available implementation. This incompatibility necessitated the re-implementation of the Matlab algorithm in a Java environment, which was by no means a trivial feat, given that Matlab is a high-level language designed for ease-of-use such as Python or R, not to mention its matrix-based semantics, which stand in stark contract to Java's object-oriented design.
#### Dataframes _**R**_ Cool
In addition to Matlab, Java, Git shell commands, there was yet another set of syntactic rules we had to learn for our project: the statistical analysis language R. Since a core element of our project revolved around machine-learning using a large dataset as a training matrix, a programmatic way of handling any manipulations to the raw dataset was required. Of course, we opted for the financially optimal open-source solution. In addition to complex matrix-wide operations, R also allowed us to construct our risk model through its support for statistical packages enabling us to compute a Cox Proportional Hazards Model from our data. Despite the addition of yet another language that we had to learn, R allowed us to develop the core back-end behavior necessary for our application to exist.
## What did we learn?
As a team, we knew next to nothing about anything that was involved in creating this app. In short, all the skills that were required to implement our product were learned on the spot as we were working. App development in android was completely alien; trying to decipher Matlab, a language none of us had ever learned, was ludicrously and unnecessarily involved; creating appealing and functional user interfaces and user experiences was a deceptively complex. In short, in order to put together Vanguard from scratch, all of us were forced to not only step out of our comfort zone, but blast out of it at terminal velocities.
## Limitations
### User Characteristics
Due to the nature of the data we had to work with, our algorithms work best with individuals who are more similar to the individuals present in our data; in practical terms, this means that adults and elderly would be more likely to receive more accurate results compared to younger users, for example.
### Relative Risk & Age
The currently implemented algorithm considers age to be a variable affecting health just like other variables such as blood pressure or serum total cholesterol levels. This produces a more accurate absolute estimation of risk, it does not provide users with an age-adjusted score. While more accurate, the downside of this choice is that user score will inevitably decrease as they age, even if they maintain their health in all other aspects, leading to a frustrating user experience over the long term.
## References
* Liu, Y., & Gopalakrishnan, V. (2017). An overview and evaluation of recent machine learning imputation methods using cardiac imaging data. Data, 2(1), 8.
*  "NHLBI, Estimate of 10-Year Risk for CHD". Nhlbi.nih.gov. Retrieved 2018-10-12.
* J. Vesanto, J.E. Himberg, E. Alhoniemi, J. Parhankangas
Report A57
SOM Toolbox for Matlab 5, Helsinki University of Technology, Finland (2000)
* L. Folguera, J. Zupan, D. Cicerone, J.F. Magallanes
Self-organizing maps for imputation of missing data in incomplete data matrices
Chemom. Intell. Lab. Syst., 143 (2015), pp. 146-151
