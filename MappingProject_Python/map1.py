import folium   #folium helps us to convert python to js css and html format byitself. else python have no such features.
import pandas
import io

data =  pandas.read_csv("volcanoes.txt")
lat = list(data["LAT"])
lon = list(data["LON"])
elv = list(data["ELEV"])


def colorGenerator(elevation):
    if elevation < 1000:
        return "green"

    elif 1000 <= elevation < 3000:
        return "orange"

    else:
        return "red"

#base layer
map = folium.Map(location=[38.58, -99.09], zoom_start=6, titles="Mapbox Bright")

fgv= folium.FeatureGroup(name="Volcaneos")

# creating a custom Marker layer for showing pins on maps using data from volcaneos.txt
for lt, ln, el in zip(lat,lon,elv):
    #fg.add_child(folium.CircleMarker(location=[lt, ln], radius=6, popup= str(el)+" m", icon= folium.Icon(color=colorGenerator(el)), color="grey", fill_opacity=0.7))
    fgv.add_child(folium.CircleMarker(location=[lt, ln], radius=6, popup= str(el)+" m",
    fill_color= colorGenerator(el), color="grey", fill_opacity=0.7))


#GeoJson is used to show population on maps , it is a polygon layer
#syntax :   l = lambda x: x**2  ---- l(5)  x=5 result 25
fgeo= folium.FeatureGroup(name="Populations")

fgeo.add_child(folium.GeoJson(data= open("world.json", "r", encoding= "utf-8-sig").read(),
style_function= lambda x : {"fillColor": "green" if x["properties"]["POP2005"] < 10000000
else "orange" if 10000000 <= x["properties"]["POP2005"] < 20000000 else "red"}))

map.add_child(fgv)
map.add_child(fgeo)

map.add_child(folium.LayerControl())

map.save("Map1.html")
