from fastapi import APIRouter
from app.services.proxy_service import *

router = APIRouter(
    prefix="/appointments",
    tags=["Appointments"]
)

@router.get("/all")
async def getAppointments():

    return await forward_get(
        "/appointments/all"
    )


@router.post("/book")
async def bookAppointment(data: dict):

    return await forward_post(
        "/appointments/book",
        data
    )


@router.delete("/delete/{id}")
async def deleteAppointment(id: int):

    return await forward_delete(
        f"/appointments/delete/{id}"
    )