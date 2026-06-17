from fastapi import APIRouter
from app.services.proxy_service import *

router = APIRouter(
    prefix="/doctors",
    tags=["Doctors"]
)

@router.get("/all")
async def getDoctors():

    return await forward_get(
        "/doctors/all"
    )