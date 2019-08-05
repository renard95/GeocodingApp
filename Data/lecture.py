import ogr, osr,osgeo
import csv


def ouverture_muni():
    liste_x=[]
    liste_y=[]
    liste_zastx=[]
    liste_zasty=[]
    inputEPSG = 5514
    outputEPSG = 4326
    j=0
    i=0
    with open('muni_copy.csv', newline='') as csvfile:
        spamreader = csv.reader(csvfile, delimiter=' ', quotechar='|')

        for i in range(771):
             i+=1
#             print(', '.join(row))
             ligne = csvfile.readline()
             X = ligne.split(',')[9]
             Y = ligne.split(',')[10]
             ZASTX = ligne.split(',')[12]
             ZASTY = ligne.split(',')[13]
             liste_x.append(X)
             liste_y.append(Y)
             liste_zastx.append(ZASTX)
             liste_zasty.append(ZASTY)

         
    with open('muni_test.csv', 'w', newline='') as csvfile:
        fieldnames = ['x', 'y', 'zastx', 'zasty']
        writer = csv.DictWriter(csvfile, fieldnames=fieldnames)
        writer.writeheader()
        while(j<i):
            
            # create a geometry from coordinates
            pointX = float(liste_x[j])
            pointY = float(liste_y[j])
            point1 = osgeo.ogr.Geometry(osgeo.ogr.wkbPoint)
            point1.AddPoint(pointX,pointY)
            if(j<758):
                pointZASTX = float(liste_zastx[j])
                pointZASTY = float(liste_zasty[j])
                point2 = osgeo.ogr.Geometry(osgeo.ogr.wkbPoint)
                point2.AddPoint(pointZASTX, pointZASTY)
            
            # create coordinate transformation
            inSpatialRef = osr.SpatialReference()
            inSpatialRef.ImportFromEPSG(inputEPSG)
            
            outSpatialRef = osr.SpatialReference()
            outSpatialRef.ImportFromEPSG(outputEPSG)
            
            coordTransform = osr.CoordinateTransformation(inSpatialRef, outSpatialRef)
            
            # transform point
            point1.Transform(coordTransform)
            point2.Transform(coordTransform)
            
            # print point in EPSG 4326
            x_final=point1.GetX()
            y_final=point1.GetY()
            zastx_final=point2.GetX()
            zasty_final=point2.GetY()
            
       
            writer.writerow({'x': x_final,'y': y_final,'zastx': zastx_final, 'zasty': zasty_final})
            j+=1
            
def ouverture_train():
    inputEPSG = 5514
    outputEPSG = 4326
    j=0
    i=0
    listeX=[]
    listeY=[]
    with open('train_copy.csv', newline='') as csvfile:
        spamreader = csv.reader(csvfile, delimiter=' ', quotechar='|')

        for i in range(2792):
             i+=1
#             print(', '.join(row))
             ligne = csvfile.readline()
#             X = ligne.split(',')[9]
#             Y = ligne.split(',')[10]
             ZASTX = ligne.split(',')[6]
             ZASTY = ligne.split(',')[7]
#             liste_x.append(X)
#             liste_y.append(Y)
             listeX.append(ZASTX)
             listeY.append(ZASTY)

        
    with open('train_test.csv', 'w', newline='') as csvfile:
        fieldnames = ['zastx', 'zasty']
        writer = csv.DictWriter(csvfile, fieldnames=fieldnames)
        writer.writeheader()
        while(j<i):
            
            # create a geometry from coordinates
#            pointX = float(liste_x[j])
#            print(j)
#            print(pointX)
#            pointY = float(liste_y[j])
#            point1 = osgeo.ogr.Geometry(osgeo.ogr.wkbPoint)
#            point1.AddPoint(pointX,pointY)
#            if(j<758):
            pointZASTX = float(listeX[j])
            pointZASTY = float(listeY[j])
            point2 = osgeo.ogr.Geometry(osgeo.ogr.wkbPoint)
            point2.AddPoint(pointZASTX, pointZASTY)
            
            # create coordinate transformation
            inSpatialRef = osr.SpatialReference()
            inSpatialRef.ImportFromEPSG(inputEPSG)
            
            outSpatialRef = osr.SpatialReference()
            outSpatialRef.ImportFromEPSG(outputEPSG)
            
            coordTransform = osr.CoordinateTransformation(inSpatialRef, outSpatialRef)
            
            # transform point
#            point1.Transform(coordTransform)
            point2.Transform(coordTransform)
            
            # print point in EPSG 4326
#            x_final=point1.GetX()
#            y_final=point1.GetY()
            zastx_final=point2.GetX()
            zasty_final=point2.GetY()
            
       
            writer.writerow({'zastx': zastx_final, 'zasty': zasty_final})
            j+=1            
            
def ouverture_oth():
    inputEPSG = 5514
    outputEPSG = 4326
    j=0
    i=0
    liste_x=[]
    liste_y=[]
    liste_zastx=[]
    liste_zasty=[]
    with open('oth_copy.csv', newline='') as csvfile:
        spamreader = csv.reader(csvfile, delimiter=' ', quotechar='|')

        for i in range(35645):
             i+=1
#             print(', '.join(row))
             ligne = csvfile.readline()
             X = ligne.split(',')[0]
             Y = ligne.split(',')[1]
             ZASTX = ligne.split(',')[2]
             ZASTY = ligne.split(',')[3]

             liste_x.append(X)
             liste_y.append(Y)

#             if i==338:
#                print(listeX[i]) 
             liste_zastx.append(ZASTX)
             liste_zasty.append(ZASTY)
             
             
    print(len(liste_zastx))
    with open('oth_test.csv', 'w', newline='') as csvfile:
        fieldnames = ['x', 'y']
        writer = csv.DictWriter(csvfile, fieldnames=fieldnames)
        writer.writeheader()
        while(j<i):
           if (liste_x[j]!="" and liste_zastx[j]==""):
            # create a geometry from coordinates

               pointX = float(liste_x[j])
               pointY = float(liste_y[j])

               point1 = osgeo.ogr.Geometry(osgeo.ogr.wkbPoint)
               point1.AddPoint(pointX,pointY)
               print(j)
           elif (liste_zastx[j]!=""):
               liste_zastx[j]
               pointX = float(liste_zastx[j])
               pointY = float(liste_zasty[j])

               point1 = osgeo.ogr.Geometry(osgeo.ogr.wkbPoint)
               point1.AddPoint(pointX,pointY)
               print(j)
               
#               if (liste_zastx[j]!=""):
#                   pointZASTX = float(liste_zastx[j])
#                   pointZASTY = float(liste_zasty[j])
#            
#                   point2 = osgeo.ogr.Geometry(osgeo.ogr.wkbPoint)
#                   point2.AddPoint(pointZASTX, pointZASTY)
#                    
                    # create coordinate transformation
           inSpatialRef = osr.SpatialReference()
           inSpatialRef.ImportFromEPSG(inputEPSG)
                        
           outSpatialRef = osr.SpatialReference()
           outSpatialRef.ImportFromEPSG(outputEPSG)
                        
           coordTransform = osr.CoordinateTransformation(inSpatialRef, outSpatialRef)
                        
                        # transform point
           point1.Transform(coordTransform)
            #           point2.Transform(coordTransform)
                        
                        # print point in EPSG 4326
           x_final=point1.GetX()
           y_final=point1.GetY()
            #           zastx_final=point2.GetX()
            #           zasty_final=point2.GetY()
                        
                   
           writer.writerow({'x': x_final, 'y': y_final})
           j+=1             
                
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            



ouverture_muni()
ouverture_train()
ouverture_oth()  


